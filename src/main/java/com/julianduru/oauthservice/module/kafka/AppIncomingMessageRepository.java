package com.julianduru.oauthservice.module.kafka;

import com.julianduru.oauthservice.entity.QueueIncomingMessage;
import com.julianduru.queueintegrationlib.model.IncomingMessage;
import com.julianduru.queueintegrationlib.module.subscribe.repo.IncomingMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by Julian Duru on 02/03/2023
 */

@Component
@RequiredArgsConstructor
public class AppIncomingMessageRepository implements IncomingMessageRepository {

    private final QueueIncomingMessageRepository incomingMessageRepository;


    @Override
    public IncomingMessage save(IncomingMessage message) {
        return incomingMessageRepository.save(QueueIncomingMessage.from(message)).toIncomingMessage();
    }

    @Override
    public List<IncomingMessage> saveAll(List<IncomingMessage> messages) {
        return incomingMessageRepository.saveAll(messages.stream().map(QueueIncomingMessage::from).toList())
            .stream().map(QueueIncomingMessage::toIncomingMessage).toList();
    }

    @Override
    public boolean existsByReference(String reference) {
        return incomingMessageRepository.existsByReference(reference);
    }

    @Override
    public boolean existsByStatus(IncomingMessage.Status status) {
        return incomingMessageRepository.existsByStatus(status);
    }

    @Override
    public Page<IncomingMessage> findByStatus(IncomingMessage.Status status, Pageable pageable) {
        return incomingMessageRepository.findByStatus(status, pageable);
    }


}
