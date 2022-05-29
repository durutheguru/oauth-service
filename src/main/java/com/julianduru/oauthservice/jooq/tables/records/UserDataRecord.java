/*
 * This file is generated by jOOQ.
 */
package com.julianduru.oauthservice.jooq.tables.records;


import com.julianduru.oauthservice.jooq.tables.UserData;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserDataRecord extends UpdatableRecordImpl<UserDataRecord> implements Record11<Long, String, String, String, String, String, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oauth_service.user_data.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>oauth_service.user_data.auth_role_id</code>.
     */
    public void setAuthRoleId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.auth_role_id</code>.
     */
    public String getAuthRoleId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oauth_service.user_data.auth_username</code>.
     */
    public void setAuthUsername(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.auth_username</code>.
     */
    public String getAuthUsername() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oauth_service.user_data.time_added</code>.
     */
    public void setTimeAdded(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.time_added</code>.
     */
    public String getTimeAdded() {
        return (String) get(3);
    }

    /**
     * Setter for <code>oauth_service.user_data.time_updated</code>.
     */
    public void setTimeUpdated(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.time_updated</code>.
     */
    public String getTimeUpdated() {
        return (String) get(4);
    }

    /**
     * Setter for <code>oauth_service.user_data.additional_info</code>.
     */
    public void setAdditionalInfo(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.additional_info</code>.
     */
    public String getAdditionalInfo() {
        return (String) get(5);
    }

    /**
     * Setter for <code>oauth_service.user_data.email</code>.
     */
    public void setEmail(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.email</code>.
     */
    public String getEmail() {
        return (String) get(6);
    }

    /**
     * Setter for <code>oauth_service.user_data.name</code>.
     */
    public void setName(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.name</code>.
     */
    public String getName() {
        return (String) get(7);
    }

    /**
     * Setter for <code>oauth_service.user_data.password</code>.
     */
    public void setPassword(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.password</code>.
     */
    public String getPassword() {
        return (String) get(8);
    }

    /**
     * Setter for <code>oauth_service.user_data.username</code>.
     */
    public void setUsername(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.username</code>.
     */
    public String getUsername() {
        return (String) get(9);
    }

    /**
     * Setter for <code>oauth_service.user_data.authorities</code>.
     */
    public void setAuthorities(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>oauth_service.user_data.authorities</code>.
     */
    public String getAuthorities() {
        return (String) get(10);
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
    public Row11<Long, String, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<Long, String, String, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return UserData.USER_DATA.ID;
    }

    @Override
    public Field<String> field2() {
        return UserData.USER_DATA.AUTH_ROLE_ID;
    }

    @Override
    public Field<String> field3() {
        return UserData.USER_DATA.AUTH_USERNAME;
    }

    @Override
    public Field<String> field4() {
        return UserData.USER_DATA.TIME_ADDED;
    }

    @Override
    public Field<String> field5() {
        return UserData.USER_DATA.TIME_UPDATED;
    }

    @Override
    public Field<String> field6() {
        return UserData.USER_DATA.ADDITIONAL_INFO;
    }

    @Override
    public Field<String> field7() {
        return UserData.USER_DATA.EMAIL;
    }

    @Override
    public Field<String> field8() {
        return UserData.USER_DATA.NAME;
    }

    @Override
    public Field<String> field9() {
        return UserData.USER_DATA.PASSWORD;
    }

    @Override
    public Field<String> field10() {
        return UserData.USER_DATA.USERNAME;
    }

    @Override
    public Field<String> field11() {
        return UserData.USER_DATA.AUTHORITIES;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getAuthRoleId();
    }

    @Override
    public String component3() {
        return getAuthUsername();
    }

    @Override
    public String component4() {
        return getTimeAdded();
    }

    @Override
    public String component5() {
        return getTimeUpdated();
    }

    @Override
    public String component6() {
        return getAdditionalInfo();
    }

    @Override
    public String component7() {
        return getEmail();
    }

    @Override
    public String component8() {
        return getName();
    }

    @Override
    public String component9() {
        return getPassword();
    }

    @Override
    public String component10() {
        return getUsername();
    }

    @Override
    public String component11() {
        return getAuthorities();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getAuthRoleId();
    }

    @Override
    public String value3() {
        return getAuthUsername();
    }

    @Override
    public String value4() {
        return getTimeAdded();
    }

    @Override
    public String value5() {
        return getTimeUpdated();
    }

    @Override
    public String value6() {
        return getAdditionalInfo();
    }

    @Override
    public String value7() {
        return getEmail();
    }

    @Override
    public String value8() {
        return getName();
    }

    @Override
    public String value9() {
        return getPassword();
    }

    @Override
    public String value10() {
        return getUsername();
    }

    @Override
    public String value11() {
        return getAuthorities();
    }

    @Override
    public UserDataRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public UserDataRecord value2(String value) {
        setAuthRoleId(value);
        return this;
    }

    @Override
    public UserDataRecord value3(String value) {
        setAuthUsername(value);
        return this;
    }

    @Override
    public UserDataRecord value4(String value) {
        setTimeAdded(value);
        return this;
    }

    @Override
    public UserDataRecord value5(String value) {
        setTimeUpdated(value);
        return this;
    }

    @Override
    public UserDataRecord value6(String value) {
        setAdditionalInfo(value);
        return this;
    }

    @Override
    public UserDataRecord value7(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UserDataRecord value8(String value) {
        setName(value);
        return this;
    }

    @Override
    public UserDataRecord value9(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UserDataRecord value10(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public UserDataRecord value11(String value) {
        setAuthorities(value);
        return this;
    }

    @Override
    public UserDataRecord values(Long value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11) {
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
     * Create a detached UserDataRecord
     */
    public UserDataRecord() {
        super(UserData.USER_DATA);
    }

    /**
     * Create a detached, initialised UserDataRecord
     */
    public UserDataRecord(Long id, String authRoleId, String authUsername, String timeAdded, String timeUpdated, String additionalInfo, String email, String name, String password, String username, String authorities) {
        super(UserData.USER_DATA);

        setId(id);
        setAuthRoleId(authRoleId);
        setAuthUsername(authUsername);
        setTimeAdded(timeAdded);
        setTimeUpdated(timeUpdated);
        setAdditionalInfo(additionalInfo);
        setEmail(email);
        setName(name);
        setPassword(password);
        setUsername(username);
        setAuthorities(authorities);
    }
}
