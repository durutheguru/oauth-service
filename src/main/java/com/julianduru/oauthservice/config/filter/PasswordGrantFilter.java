package com.julianduru.oauthservice.config.filter;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@Component
@RequiredArgsConstructor
public class PasswordGrantFilter extends OncePerRequestFilter {

    private static final String DEFAULT_AUTH_ENDPOINT_URI = "/oauth2/token";

    private final RequestMatcher authEndpointMatcher = createDefaultRequestMatcher();

//    private final JwtGenerator jwtGenerator;

    private final AuthenticationManager authenticationManager;



    @Override
    protected void doFilterInternal(
        @NotNull HttpServletRequest request,
        @NotNull HttpServletResponse response,
        @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (!this.authEndpointMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        var username = request.getParameter("username");
        var password = request.getParameter("password");
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication == null) {
            throw new BadCredentialsException("User Authentication Failed");
        }


    }


    private static RequestMatcher createDefaultRequestMatcher() {
        RequestMatcher authorizationRequestPostMatcher = new AntPathRequestMatcher(
            DEFAULT_AUTH_ENDPOINT_URI, HttpMethod.POST.name()
        );

        RequestMatcher grantTypeParameterMatcher = request ->
            Objects.equals(request.getParameter(OAuth2ParameterNames.GRANT_TYPE), "password");

        return new AndRequestMatcher(
            authorizationRequestPostMatcher, grantTypeParameterMatcher
        );
    }


}

