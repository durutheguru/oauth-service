package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.security.OAuthServiceTokenCustomizer;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.authentication.OAuth2AuthenticationValidator;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * created by julian on 09/04/2022
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfig {

    @Value("${oauth.service.config.issuer.url:http://localhost:8080}")
    private String issuerUrl;


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        applyDefaultSecurity(http);
        return http.build();
    }


    private void applyDefaultSecurity(HttpSecurity http) throws Exception {
        var configurer = new OAuth2AuthorizationServerConfigurer<HttpSecurity>();
        var endpointMatcher = configurer.getEndpointsMatcher();

        http
            .requestMatcher(endpointMatcher)
            .authorizeRequests(
                requests -> requests.anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers(endpointMatcher))
            .formLogin().loginPage("/login")
            .and()
            .apply(configurer);
    }


    private AuthenticationProvider authenticationProvider(
        RegisteredClientRepository clientRepository,
        OAuth2AuthorizationService authorizationService,
        OAuth2AuthorizationConsentService consentService
    ) {
        var provider = new OAuth2AuthorizationCodeRequestAuthenticationProvider(
            clientRepository, authorizationService, consentService
        );

        provider.setAuthenticationValidatorResolver(
            createDefaultAuthenticationValidatorResolver()
        );

        return provider;
    }


    private Function<String, OAuth2AuthenticationValidator> createDefaultAuthenticationValidatorResolver() {
        var authValidatorMap = Map.of(
            OAuth2ParameterNames.REDIRECT_URI, new RedirectUriOAuth2AuthenticationValidator()
        );

        return authValidatorMap::get;
    }


    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
         return new JdbcRegisteredClientRepository(jdbcTemplate);
    }


    @Bean
    public OAuth2AuthorizationService oAuth2AuthorizationService(
        JdbcTemplate jdbcTemplate, RegisteredClientRepository clientRepository
    ) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, clientRepository);
    }


    @Bean
    public OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService(
        JdbcTemplate jdbcTemplate, RegisteredClientRepository clientRepository
    ) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, clientRepository);
    }


    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);

        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }


    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }


    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return new OAuthServiceTokenCustomizer();
    }


    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
            .issuer(issuerUrl)
            .build();
    }


    private static RSAKey generateRsa() throws NoSuchAlgorithmException {
        var keyPair = generateRsaKey();
        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build();
    }


    private static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }


}


