package com.julianduru.oauthservice.module.kafka;

import com.julianduru.oauthservice.entity.QueueIncomingMessage;
import com.julianduru.queueintegrationlib.model.IncomingMessage;
import com.julianduru.queueintegrationlib.module.subscribe.repo.IncomingMessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * created by Julian Duru on 24/02/2023
 */
@Repository
public interface QueueIncomingMessageRepository extends JpaRepository<QueueIncomingMessage, Long> {


    boolean existsByReference(String reference);


    boolean existsByStatus(IncomingMessage.Status status);


    Page<QueueIncomingMessage> findAllByStatus(IncomingMessage.Status status, Pageable pageable);


    default Page<IncomingMessage> findByStatus(IncomingMessage.Status status, Pageable pageable) {
        return findAllByStatus(status, pageable).map(QueueIncomingMessage::toIncomingMessage);
    }


    @Modifying
    @Query(
        "UPDATE QueueIncomingMessage m SET m.status = ?1 WHERE m.id IN ?2"
    )
    Long updateStatusByIdIn(IncomingMessage.Status status, Collection<Long> ids);


}
