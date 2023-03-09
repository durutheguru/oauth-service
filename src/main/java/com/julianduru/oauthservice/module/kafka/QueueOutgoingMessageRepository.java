package com.julianduru.oauthservice.module.kafka;

import com.julianduru.oauthservice.entity.QueueOutgoingMessage;
import com.julianduru.queueintegrationlib.model.OutgoingMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * created by Julian Duru on 23/02/2023
 */
@Repository
public interface QueueOutgoingMessageRepository extends JpaRepository<QueueOutgoingMessage, Long> {


    boolean existsByReference(String reference);


    boolean existsByReferenceAndStatus(String reference, OutgoingMessage.Status status);


    boolean existsByStatus(OutgoingMessage.Status status);


    Page<OutgoingMessage> findByStatus(OutgoingMessage.Status status, Pageable pageable);


    @Modifying
    @Query(
        "UPDATE QueueOutgoingMessage m SET m.status = ?1 WHERE m.id IN ?2"
    )
    Long updateStatusByIdIn(OutgoingMessage.Status status, Collection<Long> ids);


}

