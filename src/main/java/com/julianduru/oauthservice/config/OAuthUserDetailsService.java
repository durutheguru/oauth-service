package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.config.properties.BootstrapProperties;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian on 28/05/2022
 */
@Slf4j
@RequiredArgsConstructor
public class OAuthUserDetailsService implements UserDetailsService {


    private final PasswordEncoder passwordEncoder;


    private final UserDataRepository userDataRepository;


    private final BootstrapProperties bootstrapProperties;


    protected void init() {
        if (userDataRepository.existsByUsername(bootstrapProperties.getAdminUsername())) {
            log.info("Admin user already registered. Skipping initialization..");
            return;
        }

        var userData = new UserData();

        userData.setUsername(bootstrapProperties.getAdminUsername());
        userData.setPassword(passwordEncoder.encode(bootstrapProperties.getAdminPassword()));
        userData.setFirstName(bootstrapProperties.getAdminFirstName());
        userData.setLastName(bootstrapProperties.getAdminLastName());
        userData.setEmail(bootstrapProperties.getAdminEmail());
        userData.setAuthorities(
            new ArrayList<>(List.of("ADMIN"))
        );

        userDataRepository.save(userData);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDataRepository.findByUsername(username)
            .map(
                data -> User.builder()
                    .username(data.getUsername())
                    .password(data.getPassword())
                    .authorities(
                        new ArrayList<>(
                            data.getAuthorities().stream().map(SimpleGrantedAuthority::new).toList()
                        )
                    )
                    .accountLocked(data.isLocked())
                    .credentialsExpired(data.isCredentialsExpired())
                    .build()
            )
            .orElseThrow(
                () -> new UsernameNotFoundException(String.format("Username %s not found", username))
            );
    }


}



