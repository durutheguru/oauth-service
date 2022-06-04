/*
 * This file is generated by jOOQ.
 */
package com.julianduru.oauthservice.jooq;


import com.julianduru.oauthservice.jooq.tables.Databasechangeloglock;
import com.julianduru.oauthservice.jooq.tables.Oauth2Authorization;
import com.julianduru.oauthservice.jooq.tables.Oauth2AuthorizationConsent;
import com.julianduru.oauthservice.jooq.tables.Oauth2RegisteredClient;
import com.julianduru.oauthservice.jooq.tables.ResourceServer;
import com.julianduru.oauthservice.jooq.tables.ResourceServerAllowedScopes;
import com.julianduru.oauthservice.jooq.tables.UserData;
import com.julianduru.oauthservice.jooq.tables.records.DatabasechangeloglockRecord;
import com.julianduru.oauthservice.jooq.tables.records.Oauth2AuthorizationConsentRecord;
import com.julianduru.oauthservice.jooq.tables.records.Oauth2AuthorizationRecord;
import com.julianduru.oauthservice.jooq.tables.records.Oauth2RegisteredClientRecord;
import com.julianduru.oauthservice.jooq.tables.records.ResourceServerAllowedScopesRecord;
import com.julianduru.oauthservice.jooq.tables.records.ResourceServerRecord;
import com.julianduru.oauthservice.jooq.tables.records.UserDataRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * oauth_service.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<DatabasechangeloglockRecord> KEY_DATABASECHANGELOGLOCK_PRIMARY = Internal.createUniqueKey(Databasechangeloglock.DATABASECHANGELOGLOCK, DSL.name("KEY_DATABASECHANGELOGLOCK_PRIMARY"), new TableField[] { Databasechangeloglock.DATABASECHANGELOGLOCK.ID }, true);
    public static final UniqueKey<Oauth2AuthorizationRecord> KEY_OAUTH2_AUTHORIZATION_PRIMARY = Internal.createUniqueKey(Oauth2Authorization.OAUTH2_AUTHORIZATION, DSL.name("KEY_oauth2_authorization_PRIMARY"), new TableField[] { Oauth2Authorization.OAUTH2_AUTHORIZATION.ID }, true);
    public static final UniqueKey<Oauth2AuthorizationConsentRecord> KEY_OAUTH2_AUTHORIZATION_CONSENT_PRIMARY = Internal.createUniqueKey(Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT, DSL.name("KEY_oauth2_authorization_consent_PRIMARY"), new TableField[] { Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT.REGISTERED_CLIENT_ID, Oauth2AuthorizationConsent.OAUTH2_AUTHORIZATION_CONSENT.PRINCIPAL_NAME }, true);
    public static final UniqueKey<Oauth2RegisteredClientRecord> KEY_OAUTH2_REGISTERED_CLIENT_PRIMARY = Internal.createUniqueKey(Oauth2RegisteredClient.OAUTH2_REGISTERED_CLIENT, DSL.name("KEY_oauth2_registered_client_PRIMARY"), new TableField[] { Oauth2RegisteredClient.OAUTH2_REGISTERED_CLIENT.ID }, true);
    public static final UniqueKey<Oauth2RegisteredClientRecord> KEY_OAUTH2_REGISTERED_CLIENT_UNIQUE_REGISTERED_CLIENT_ID_03FA7B9A_905F_4B59 = Internal.createUniqueKey(Oauth2RegisteredClient.OAUTH2_REGISTERED_CLIENT, DSL.name("KEY_oauth2_registered_client_UNIQUE_registered_client_id_03fa7b9a-905f-4b59"), new TableField[] { Oauth2RegisteredClient.OAUTH2_REGISTERED_CLIENT.CLIENT_ID }, true);
    public static final UniqueKey<ResourceServerRecord> KEY_RESOURCE_SERVER_PRIMARY = Internal.createUniqueKey(ResourceServer.RESOURCE_SERVER, DSL.name("KEY_resource_server_PRIMARY"), new TableField[] { ResourceServer.RESOURCE_SERVER.ID }, true);
    public static final UniqueKey<ResourceServerRecord> KEY_RESOURCE_SERVER_UC_RESOURCE_SERVERRESOURCE_SERVER_ID_COL = Internal.createUniqueKey(ResourceServer.RESOURCE_SERVER, DSL.name("KEY_resource_server_UC_RESOURCE_SERVERRESOURCE_SERVER_ID_COL"), new TableField[] { ResourceServer.RESOURCE_SERVER.RESOURCE_SERVER_ID }, true);
    public static final UniqueKey<UserDataRecord> KEY_USER_DATA_PRIMARY = Internal.createUniqueKey(UserData.USER_DATA, DSL.name("KEY_user_data_PRIMARY"), new TableField[] { UserData.USER_DATA.ID }, true);
    public static final UniqueKey<UserDataRecord> KEY_USER_DATA_UC_USER_DATAEMAIL_COL = Internal.createUniqueKey(UserData.USER_DATA, DSL.name("KEY_user_data_UC_USER_DATAEMAIL_COL"), new TableField[] { UserData.USER_DATA.EMAIL }, true);
    public static final UniqueKey<UserDataRecord> KEY_USER_DATA_UC_USER_DATAUSERNAME_COL = Internal.createUniqueKey(UserData.USER_DATA, DSL.name("KEY_user_data_UC_USER_DATAUSERNAME_COL"), new TableField[] { UserData.USER_DATA.USERNAME }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ResourceServerAllowedScopesRecord, ResourceServerRecord> FKHF7RY24VOX7A9PO1E108JD3DG = Internal.createForeignKey(ResourceServerAllowedScopes.RESOURCE_SERVER_ALLOWED_SCOPES, DSL.name("FKhf7ry24vox7a9po1e108jd3dg"), new TableField[] { ResourceServerAllowedScopes.RESOURCE_SERVER_ALLOWED_SCOPES.RESOURCE_SERVER_ID }, Keys.KEY_RESOURCE_SERVER_PRIMARY, new TableField[] { ResourceServer.RESOURCE_SERVER.ID }, true);
}