package com.julianduru.oauthservice.repository;

import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.entity.ServerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * created by julian on 24/04/2022
 */
@Repository
public interface ResourceServerRepository extends JpaRepository<ResourceServer, Long> {


    boolean existsByResourceServerId(String id);


    Optional<ResourceServer> findByResourceServerId(String id);


    Optional<ResourceServer> findByResourceServerIdAndStatus(String id, ServerStatus status);



}
