package com.julianduru.oauthservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.julianduru.queueintegrationlib.model.IncomingMessage;
import com.julianduru.queueintegrationlib.model.OperationStatus;
import com.julianduru.util.jpa.ZonedDateTimeStringConverter;
import com.julianduru.util.json.ZonedDateTimeDeserializer;
import com.julianduru.util.json.ZonedDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * created by Julian Duru on 02/03/2023
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueIncomingMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true, length = 200)
    private String reference;


    @Column(nullable = false, length = 150)
    private String topic;


    @Column(columnDefinition = "TEXT")
    private String header;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private IncomingMessage.Status status;


    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "status", column = @Column(name = "processing_status")),
        @AttributeOverride(name = "statusMessage", column = @Column(name = "processing_status_message", columnDefinition = "TEXT"))
    })
    private OperationStatus processingStatus;


    private boolean treatImmediately;


    @Column(nullable = false, updatable = false)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @Convert(converter = ZonedDateTimeStringConverter.class)
    private ZonedDateTime timeAdded;


    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @Convert(converter = ZonedDateTimeStringConverter.class)
    private ZonedDateTime timeUpdated;


    @PrePersist
    public void prePersist() {
        timeAdded = ZonedDateTime.now();
    }


    @PreUpdate
    public void preUpdate() {
        timeUpdated = ZonedDateTime.now();
    }



    public static QueueIncomingMessage from(IncomingMessage message) {
        var msg = QueueIncomingMessage.builder()
                .reference(message.getReference())
                .topic(message.getTopic())
                .header(message.getHeader())
                .payload(message.getPayload())
                .status(message.getStatus())
                .processingStatus(message.getProcessingStatus())
                .treatImmediately(message.isTreatImmediately())
                .build();

        if (message.getId() != null) {
            msg.setId(Long.parseLong(message.getId()));
        }

        return msg;
    }


    public IncomingMessage toIncomingMessage() {
        var msg = IncomingMessage.builder()
                .reference(getReference())
                .topic(getTopic())
                .header(getHeader())
                .payload(getPayload())
                .status(getStatus())
                .processingStatus(getProcessingStatus())
                .treatImmediately(isTreatImmediately())
                .build();

        if (getId() != null) {
            msg.setId(getId().toString());
        }

        return msg;
    }



}

