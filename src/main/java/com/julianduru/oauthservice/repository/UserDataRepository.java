package com.julianduru.oauthservice.repository;

import com.julianduru.oauthservice.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian on 28/05/2022
 */
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {


    Optional<UserData> findByUsername(String username);


    boolean existsByUsername(String username);


    boolean existsByEmail(String email);


    Optional<UserData> findByUsernameAndPassword(String username, String password);


}


