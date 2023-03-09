package com.julianduru.oauthservice.module.kafka;

import com.julianduru.oauthservice.entity.QueueOutgoingMessage;
import com.julianduru.queueintegrationlib.model.OutgoingMessage;
import com.julianduru.queueintegrationlib.module.publish.repo.OutgoingMessageRepository;
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
public class AppOutgoingMessageRepository implements OutgoingMessageRepository {

    private final QueueOutgoingMessageRepository outgoingMessageRepository;



    @Override
    public OutgoingMessage save(OutgoingMessage message) {
        return outgoingMessageRepository.save(QueueOutgoingMessage.from(message)).toOutgoingMessage();
    }

    @Override
    public List<OutgoingMessage> saveAll(List<OutgoingMessage> messages) {
        return outgoingMessageRepository.saveAll(messages.stream().map(QueueOutgoingMessage::from).toList())
            .stream().map(QueueOutgoingMessage::toOutgoingMessage).toList();
    }

    @Override
    public boolean existsByReference(String reference) {
        return outgoingMessageRepository.existsByReference(reference);
    }

    @Override
    public boolean existsByReferenceAndStatus(String reference, OutgoingMessage.Status status) {
        return outgoingMessageRepository.existsByReferenceAndStatus(reference, status);
    }

    @Override
    public boolean existsByStatus(OutgoingMessage.Status status) {
        return outgoingMessageRepository.existsByStatus(status);
    }

    @Override
    public Page<OutgoingMessage> findByStatus(OutgoingMessage.Status status, Pageable pageable) {
        return outgoingMessageRepository.findByStatus(status, pageable);
    }


}
