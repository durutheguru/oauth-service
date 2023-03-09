/*
 * This file is generated by jOOQ.
 */
package com.julianduru.oauthservice.jooq.tables.records;


import com.julianduru.oauthservice.jooq.tables.QueueOutgoingMessage;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class QueueOutgoingMessageRecord extends UpdatableRecordImpl<QueueOutgoingMessageRecord> implements Record11<Long, String, String, String, String, Integer, String, String, String, String, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.time_added</code>.
     */
    public void setTimeAdded(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.time_added</code>.
     */
    public String getTimeAdded() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.time_updated</code>.
     */
    public void setTimeUpdated(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.time_updated</code>.
     */
    public String getTimeUpdated() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.header</code>.
     */
    public void setHeader(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.header</code>.
     */
    public String getHeader() {
        return (String) get(3);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.payload</code>.
     */
    public void setPayload(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.payload</code>.
     */
    public String getPayload() {
        return (String) get(4);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.processing_status</code>.
     */
    public void setProcessingStatus(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.processing_status</code>.
     */
    public Integer getProcessingStatus() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.processing_status_message</code>.
     */
    public void setProcessingStatusMessage(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.processing_status_message</code>.
     */
    public String getProcessingStatusMessage() {
        return (String) get(6);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.reference</code>.
     */
    public void setReference(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.reference</code>.
     */
    public String getReference() {
        return (String) get(7);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.status</code>.
     */
    public void setStatus(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.status</code>.
     */
    public String getStatus() {
        return (String) get(8);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.topic</code>.
     */
    public void setTopic(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.topic</code>.
     */
    public String getTopic() {
        return (String) get(9);
    }

    /**
     * Setter for <code>oauth_service.queue_outgoing_message.treat_immediately</code>.
     */
    public void setTreatImmediately(Boolean value) {
        set(10, value);
    }

    /**
     * Getter for <code>oauth_service.queue_outgoing_message.treat_immediately</code>.
     */
    public Boolean getTreatImmediately() {
        return (Boolean) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<Long, String, String, String, String, Integer, String, String, String, String, Boolean> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<Long, String, String, String, String, Integer, String, String, String, String, Boolean> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.ID;
    }

    @Override
    public Field<String> field2() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.TIME_ADDED;
    }

    @Override
    public Field<String> field3() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.TIME_UPDATED;
    }

    @Override
    public Field<String> field4() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.HEADER;
    }

    @Override
    public Field<String> field5() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.PAYLOAD;
    }

    @Override
    public Field<Integer> field6() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.PROCESSING_STATUS;
    }

    @Override
    public Field<String> field7() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.PROCESSING_STATUS_MESSAGE;
    }

    @Override
    public Field<String> field8() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.REFERENCE;
    }

    @Override
    public Field<String> field9() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.STATUS;
    }

    @Override
    public Field<String> field10() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.TOPIC;
    }

    @Override
    public Field<Boolean> field11() {
        return QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE.TREAT_IMMEDIATELY;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTimeAdded();
    }

    @Override
    public String component3() {
        return getTimeUpdated();
    }

    @Override
    public String component4() {
        return getHeader();
    }

    @Override
    public String component5() {
        return getPayload();
    }

    @Override
    public Integer component6() {
        return getProcessingStatus();
    }

    @Override
    public String component7() {
        return getProcessingStatusMessage();
    }

    @Override
    public String component8() {
        return getReference();
    }

    @Override
    public String component9() {
        return getStatus();
    }

    @Override
    public String component10() {
        return getTopic();
    }

    @Override
    public Boolean component11() {
        return getTreatImmediately();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTimeAdded();
    }

    @Override
    public String value3() {
        return getTimeUpdated();
    }

    @Override
    public String value4() {
        return getHeader();
    }

    @Override
    public String value5() {
        return getPayload();
    }

    @Override
    public Integer value6() {
        return getProcessingStatus();
    }

    @Override
    public String value7() {
        return getProcessingStatusMessage();
    }

    @Override
    public String value8() {
        return getReference();
    }

    @Override
    public String value9() {
        return getStatus();
    }

    @Override
    public String value10() {
        return getTopic();
    }

    @Override
    public Boolean value11() {
        return getTreatImmediately();
    }

    @Override
    public QueueOutgoingMessageRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value2(String value) {
        setTimeAdded(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value3(String value) {
        setTimeUpdated(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value4(String value) {
        setHeader(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value5(String value) {
        setPayload(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value6(Integer value) {
        setProcessingStatus(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value7(String value) {
        setProcessingStatusMessage(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value8(String value) {
        setReference(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value9(String value) {
        setStatus(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value10(String value) {
        setTopic(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord value11(Boolean value) {
        setTreatImmediately(value);
        return this;
    }

    @Override
    public QueueOutgoingMessageRecord values(Long value1, String value2, String value3, String value4, String value5, Integer value6, String value7, String value8, String value9, String value10, Boolean value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QueueOutgoingMessageRecord
     */
    public QueueOutgoingMessageRecord() {
        super(QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE);
    }

    /**
     * Create a detached, initialised QueueOutgoingMessageRecord
     */
    public QueueOutgoingMessageRecord(Long id, String timeAdded, String timeUpdated, String header, String payload, Integer processingStatus, String processingStatusMessage, String reference, String status, String topic, Boolean treatImmediately) {
        super(QueueOutgoingMessage.QUEUE_OUTGOING_MESSAGE);

        setId(id);
        setTimeAdded(timeAdded);
        setTimeUpdated(timeUpdated);
        setHeader(header);
        setPayload(payload);
        setProcessingStatus(processingStatus);
        setProcessingStatusMessage(processingStatusMessage);
        setReference(reference);
        setStatus(status);
        setTopic(topic);
        setTreatImmediately(treatImmediately);
    }
}
