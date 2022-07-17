package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.AuthServerConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * created by julian on 29/05/2022
 */
public class OAuthServiceTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {


    @Override
    public void customize(JwtEncodingContext context) {
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            processAccessToken(context);
        }
    }


    private void processAccessToken(JwtEncodingContext context) {
        context.getClaims().claims(
            claims -> {
                addAudience(claims, context);
                addAuthorities(claims, context);
            }
        );
    }


    @SuppressWarnings("unchecked")
    private void addAudience(Map<String, Object> claims, JwtEncodingContext context) {
        var client = context.getRegisteredClient();
        var clientSettings = client.getClientSettings();
        var auds = new ArrayList<>((List<String>) claims.get(JwtClaimNames.AUD));

        if (clientSettings.getSetting(AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES) != null) {
            var allowedResourcesValue = (String)clientSettings.getSetting(
                AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES
            );
            var allowedResourcesArray = allowedResourcesValue.split("\\s*,\\s*");

            auds.addAll(Arrays.asList(allowedResourcesArray));
        }

        claims.put(JwtClaimNames.AUD, auds);
    }


    private void addAuthorities(Map<String, Object> claims, JwtEncodingContext context) {
        var principal = context.getPrincipal();
        if (principal instanceof UsernamePasswordAuthenticationToken token) {
            var authorities = token.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

            claims.put("auth", authorities);
        }
    }


}

