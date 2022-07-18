/*
 * This file is generated by jOOQ.
 */
package com.julianduru.oauthservice.jooq.tables.records;


import com.julianduru.oauthservice.jooq.tables.Oauth2AuthorizationConsent;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Oauth2AuthorizationConsentRecord extends UpdatableRecordImpl<Oauth2AuthorizationConsentRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oauth_service.oauth2_authorization_consent.registered_client_id</code>.
     */
    public void setRegisteredClientId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>oauth_service.oauth2_authorization_consent.registered_client_id</code>.
     */
    public String getRegisteredClientId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>oauth_service.oauth2_authorization_consent.principal_name</code>.
     */
    public void setPrincipalName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oauth_service.oauth2_authorization_consent.principal_name</code>.
     */
    public String getPrincipalName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oauth_service.oauth2_authorization_consent.authorities</code>.
     */
    public void setAuthorities(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oauth_service.oauth2_authorization_consent.authorities</code>.
     */
    public String getAuthorities() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT.REGISTERED_CLIENT_ID;
    }

    @Override
    public Field<String> field2() {
        return Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT.PRINCIPAL_NAME;
    }

    @Override
    public Field<String> field3() {
        return Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT.AUTHORITIES;
    }

    @Override
    public String component1() {
        return getRegisteredClientId();
    }

    @Override
    public String component2() {
        return getPrincipalName();
    }

    @Override
    public String component3() {
        return getAuthorities();
    }

    @Override
    public String value1() {
        return getRegisteredClientId();
    }

    @Override
    public String value2() {
        return getPrincipalName();
    }

    @Override
    public String value3() {
        return getAuthorities();
    }

    @Override
    public Oauth2AuthorizationConsentRecord value1(String value) {
        setRegisteredClientId(value);
        return this;
    }

    @Override
    public Oauth2AuthorizationConsentRecord value2(String value) {
        setPrincipalName(value);
        return this;
    }

    @Override
    public Oauth2AuthorizationConsentRecord value3(String value) {
        setAuthorities(value);
        return this;
    }

    @Override
    public Oauth2AuthorizationConsentRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached Oauth2AuthorizationConsentRecord
     */
    public Oauth2AuthorizationConsentRecord() {
        super(Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT);
    }

    /**
     * Create a detached, initialised Oauth2AuthorizationConsentRecord
     */
    public Oauth2AuthorizationConsentRecord(String registeredClientId, String principalName, String authorities) {
        super(Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT);

        setRegisteredClientId(registeredClientId);
        setPrincipalName(principalName);
        setAuthorities(authorities);
    }
}
