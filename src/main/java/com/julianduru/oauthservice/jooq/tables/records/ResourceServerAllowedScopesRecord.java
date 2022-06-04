/*
 * This file is generated by jOOQ.
 */
package com.julianduru.oauthservice.jooq.tables.records;


import com.julianduru.oauthservice.jooq.tables.ResourceServerAllowedScopes;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ResourceServerAllowedScopesRecord extends TableRecordImpl<ResourceServerAllowedScopesRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oauth_service.resource_server_allowed_scopes.resource_server_id</code>.
     */
    public void setResourceServerId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>oauth_service.resource_server_allowed_scopes.resource_server_id</code>.
     */
    public Long getResourceServerId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>oauth_service.resource_server_allowed_scopes.allowed_scopes</code>.
     */
    public void setAllowedScopes(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oauth_service.resource_server_allowed_scopes.allowed_scopes</code>.
     */
    public String getAllowedScopes() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ResourceServerAllowedScopes.RESOURCE_SERVER_ALLOWED_SCOPES.RESOURCE_SERVER_ID;
    }

    @Override
    public Field<String> field2() {
        return ResourceServerAllowedScopes.RESOURCE_SERVER_ALLOWED_SCOPES.ALLOWED_SCOPES;
    }

    @Override
    public Long component1() {
        return getResourceServerId();
    }

    @Override
    public String component2() {
        return getAllowedScopes();
    }

    @Override
    public Long value1() {
        return getResourceServerId();
    }

    @Override
    public String value2() {
        return getAllowedScopes();
    }

    @Override
    public ResourceServerAllowedScopesRecord value1(Long value) {
        setResourceServerId(value);
        return this;
    }

    @Override
    public ResourceServerAllowedScopesRecord value2(String value) {
        setAllowedScopes(value);
        return this;
    }

    @Override
    public ResourceServerAllowedScopesRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ResourceServerAllowedScopesRecord
     */
    public ResourceServerAllowedScopesRecord() {
        super(ResourceServerAllowedScopes.RESOURCE_SERVER_ALLOWED_SCOPES);
    }

    /**
     * Create a detached, initialised ResourceServerAllowedScopesRecord
     */
    public ResourceServerAllowedScopesRecord(Long resourceServerId, String allowedScopes) {
        super(ResourceServerAllowedScopes.RESOURCE_SERVER_ALLOWED_SCOPES);

        setResourceServerId(resourceServerId);
        setAllowedScopes(allowedScopes);
    }
}