/*
 * This file is generated by jOOQ.
 */
package com.julianduru.oauthservice.jooq.tables;


import com.julianduru.oauthservice.jooq.Keys;
import com.julianduru.oauthservice.jooq.OauthService;
import com.julianduru.oauthservice.jooq.tables.records.QueueOutgoingMessageRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row11;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class QueueOutgoingMessage extends TableImpl<QueueOutgoingMessageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oauth_service.queue_outgoing_message</code>
     */
    public static final QueueOutgoingMessage QUEUE_OUTGOING_MESSAGE = new QueueOutgoingMessage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QueueOutgoingMessageRecord> getRecordType() {
        return QueueOutgoingMessageRecord.class;
    }

    /**
     * The column <code>oauth_service.queue_outgoing_message.id</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.time_added</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> TIME_ADDED = createField(DSL.name("time_added"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.time_updated</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> TIME_UPDATED = createField(DSL.name("time_updated"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.header</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> HEADER = createField(DSL.name("header"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.payload</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> PAYLOAD = createField(DSL.name("payload"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.processing_status</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, Integer> PROCESSING_STATUS = createField(DSL.name("processing_status"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.processing_status_message</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> PROCESSING_STATUS_MESSAGE = createField(DSL.name("processing_status_message"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.reference</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> REFERENCE = createField(DSL.name("reference"), SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.status</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.topic</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, String> TOPIC = createField(DSL.name("topic"), SQLDataType.VARCHAR(150).nullable(false), this, "");

    /**
     * The column <code>oauth_service.queue_outgoing_message.treat_immediately</code>.
     */
    public final TableField<QueueOutgoingMessageRecord, Boolean> TREAT_IMMEDIATELY = createField(DSL.name("treat_immediately"), SQLDataType.BIT, this, "");

    private QueueOutgoingMessage(Name alias, Table<QueueOutgoingMessageRecord> aliased) {
        this(alias, aliased, null);
    }

    private QueueOutgoingMessage(Name alias, Table<QueueOutgoingMessageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oauth_service.queue_outgoing_message</code> table reference
     */
    public QueueOutgoingMessage(String alias) {
        this(DSL.name(alias), QUEUE_OUTGOING_MESSAGE);
    }

    /**
     * Create an aliased <code>oauth_service.queue_outgoing_message</code> table reference
     */
    public QueueOutgoingMessage(Name alias) {
        this(alias, QUEUE_OUTGOING_MESSAGE);
    }

    /**
     * Create a <code>oauth_service.queue_outgoing_message</code> table reference
     */
    public QueueOutgoingMessage() {
        this(DSL.name("queue_outgoing_message"), null);
    }

    public <O extends Record> QueueOutgoingMessage(Table<O> child, ForeignKey<O, QueueOutgoingMessageRecord> key) {
        super(child, key, QUEUE_OUTGOING_MESSAGE);
    }

    @Override
    public Schema getSchema() {
        return OauthService.OAUTH_SERVICE;
    }

    @Override
    public Identity<QueueOutgoingMessageRecord, Long> getIdentity() {
        return (Identity<QueueOutgoingMessageRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<QueueOutgoingMessageRecord> getPrimaryKey() {
        return Keys.KEY_QUEUE_OUTGOING_MESSAGE_PRIMARY;
    }

    @Override
    public List<UniqueKey<QueueOutgoingMessageRecord>> getKeys() {
        return Arrays.<UniqueKey<QueueOutgoingMessageRecord>>asList(Keys.KEY_QUEUE_OUTGOING_MESSAGE_PRIMARY, Keys.KEY_QUEUE_OUTGOING_MESSAGE_UC_QUEUE_OUTGOING_MESSAGEREFERENCE_COL);
    }

    @Override
    public QueueOutgoingMessage as(String alias) {
        return new QueueOutgoingMessage(DSL.name(alias), this);
    }

    @Override
    public QueueOutgoingMessage as(Name alias) {
        return new QueueOutgoingMessage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public QueueOutgoingMessage rename(String name) {
        return new QueueOutgoingMessage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public QueueOutgoingMessage rename(Name name) {
        return new QueueOutgoingMessage(name, null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row11<Long, String, String, String, String, Integer, String, String, String, String, Boolean> fieldsRow() {
        return (Row11) super.fieldsRow();
    }
}