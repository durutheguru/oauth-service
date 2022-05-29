package com.julianduru.oauthservice.security;

import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * created by julian on 28/05/2022
 */
@Component
@RequiredArgsConstructor
public class OAuthUserDetailsService implements UserDetailsService {


    private final PasswordEncoder passwordEncoder;


    private final UserDataRepository userDataRepository;


    @PostConstruct
    public void init() {
        var userData = new UserData();

        userData.setUsername("admin1");
        userData.setPassword(passwordEncoder.encode("password1"));
        userData.setName("ADMIN ONE");
        userData.setEmail("admin@test.com");
        userData.setAuthorities(List.of("USER"));

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
                        data.getAuthorities().stream().map(SimpleGrantedAuthority::new).toList()
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


