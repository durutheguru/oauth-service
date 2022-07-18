package com.julianduru.oauthservice.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


/**
 *
 */
public final class JwtGenerator implements OAuth2TokenGenerator<Jwt> {

    private final JwtEncoder jwtEncoder;

    private OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer;


    public JwtGenerator(
        JWKSource<SecurityContext> jwkSource, OAuth2TokenCustomizer<JwtEncodingContext> accessTokenCustomizer
    ) {
        Assert.notNull(jwkSource, "jwkSource cannot be null");
        Assert.notNull(accessTokenCustomizer, "jwtCustomizer cannot be null");

        this.jwtEncoder = new NimbusJwtEncoder(jwkSource);
        this.jwtCustomizer = accessTokenCustomizer;
    }


    @Nullable
    public Jwt generate(OAuth2TokenContext context) {
        if (context.getTokenType() != null && (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) || "id_token".equals(context.getTokenType().getValue()))) {
            if (!OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                return null;
            } else {
                String issuer = null;
                if (context.getProviderContext() != null) {
                    issuer = context.getProviderContext().getIssuer();
                }

                RegisteredClient registeredClient = context.getRegisteredClient();
                Instant issuedAt = Instant.now();
                Instant expiresAt;
                if ("id_token".equals(context.getTokenType().getValue())) {
                    expiresAt = issuedAt.plus(30L, ChronoUnit.MINUTES);
                } else {
                    expiresAt = issuedAt.plus(registeredClient.getTokenSettings().getAccessTokenTimeToLive());
                }

                JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder();
                if (StringUtils.hasText(issuer)) {
                    claimsBuilder.issuer(issuer);
                }

                claimsBuilder.subject(context.getPrincipal().getName()).audience(Collections.singletonList(registeredClient.getClientId())).issuedAt(issuedAt).expiresAt(expiresAt);
                if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                    claimsBuilder.notBefore(issuedAt);
                    if (!CollectionUtils.isEmpty(context.getAuthorizedScopes())) {
                        claimsBuilder.claim("scope", context.getAuthorizedScopes());
                    }
                } else if ("id_token".equals(context.getTokenType().getValue())) {
                    claimsBuilder.claim("azp", registeredClient.getClientId());
                    if (AuthorizationGrantType.AUTHORIZATION_CODE.equals(context.getAuthorizationGrantType())) {
                        OAuth2AuthorizationRequest authorizationRequest = (OAuth2AuthorizationRequest)context.getAuthorization().getAttribute(OAuth2AuthorizationRequest.class.getName());
                        String nonce = (String)authorizationRequest.getAdditionalParameters().get("nonce");
                        if (StringUtils.hasText(nonce)) {
                            claimsBuilder.claim("nonce", nonce);
                        }
                    }
                }

                org.springframework.security.oauth2.jwt.JwsHeader.Builder headersBuilder = JwsHeader.with(SignatureAlgorithm.RS256);
                if (this.jwtCustomizer != null) {
                    JwtEncodingContext.Builder jwtContextBuilder =
                        JwtEncodingContext.with(headersBuilder, claimsBuilder)
                            .registeredClient(context.getRegisteredClient())
                            .principal(context.getPrincipal())
                            .providerContext(context.getProviderContext())
                            .authorizedScopes(context.getAuthorizedScopes())
                            .tokenType(context.getTokenType())
                            .authorizationGrantType(context.getAuthorizationGrantType());

                    if (context.getAuthorization() != null) {
                        jwtContextBuilder.authorization(context.getAuthorization());
                    }

                    if (context.getAuthorizationGrant() != null) {
                        jwtContextBuilder.authorizationGrant(context.getAuthorizationGrant());
                    }

                    JwtEncodingContext jwtContext = jwtContextBuilder.build();
                    this.jwtCustomizer.customize(jwtContext);
                }

                JwsHeader headers = headersBuilder.build();
                JwtClaimsSet claims = claimsBuilder.build();
                Jwt jwt = this.jwtEncoder.encode(JwtEncoderParameters.from(headers, claims));
                return jwt;
            }
        } else {
            return null;
        }
    }


}

