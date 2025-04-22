CREATE SCHEMA user;

CREATE TABLE user.user_entity (
  id 			bigint NOT NULL,
  created_at 	datetime(6) DEFAULT NULL,
  email 		varchar(255) DEFAULT NULL,
  name 			varchar(255) DEFAULT NULL,
  password 		varchar(255) DEFAULT NULL,
  type 			varchar(255) DEFAULT NULL,
  status 		varchar(30) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_4xad1enskw4j1t2866f7sodrx (email)
);

create table user.users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null
);

create table user.authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references user.users(username)
);

create unique index ix_auth_username on user.authorities (username,authority);

create table user.groups (
	id bigint primary key,
	group_name varchar(50) not null
);

create table user.group_authorities (
	group_id bigint not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references user.groups(id)
);

create table user.group_members (
	id bigint primary key,
	username varchar(50) not null,
	group_id bigint not null,
	constraint fk_group_members_group foreign key(group_id) references user.groups(id)
);

create table user.persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

CREATE TABLE user.oauth2_authorized_client (
  client_registration_id varchar(100) NOT NULL,
  principal_name varchar(200) NOT NULL,
  access_token_type varchar(100) NOT NULL,
  access_token_value blob NOT NULL,
  access_token_issued_at timestamp NOT NULL,
  access_token_expires_at timestamp NOT NULL,
  access_token_scopes varchar(1000) DEFAULT NULL,
  refresh_token_value blob DEFAULT NULL,
  refresh_token_issued_at timestamp DEFAULT NULL,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (client_registration_id, principal_name)
);

CREATE TABLE user.oauth2_authorization (
  id 							varchar(100) NOT NULL,
  registered_client_id 			varchar(100) NOT NULL,
  principal_name 				varchar(200) NOT NULL,
  authorization_grant_type 		varchar(100) NOT NULL,
  authorized_scopes 			varchar(1000) DEFAULT NULL,
  attributes 					blob,
  state 						varchar(500) DEFAULT NULL,
  authorization_code_value 		blob,
  authorization_code_issued_at 	timestamp NULL DEFAULT NULL,
  authorization_code_expires_at timestamp NULL DEFAULT NULL,
  authorization_code_metadata 	blob,
  access_token_value 			blob,
  access_token_issued_at 		timestamp NULL DEFAULT NULL,
  access_token_expires_at 		timestamp NULL DEFAULT NULL,
  access_token_metadata 		blob,
  access_token_type 			varchar(100) DEFAULT NULL,
  access_token_scopes 			varchar(1000) DEFAULT NULL,
  oidc_id_token_value 			blob,
  oidc_id_token_issued_at 		timestamp NULL DEFAULT NULL,
  oidc_id_token_expires_at 		timestamp NULL DEFAULT NULL,
  oidc_id_token_metadata 		blob,
  refresh_token_value 			blob,
  refresh_token_issued_at 		timestamp NULL DEFAULT NULL,
  refresh_token_expires_at 		timestamp NULL DEFAULT NULL,
  refresh_token_metadata 		blob,
  user_code_value 				blob,
  user_code_issued_at 			timestamp NULL DEFAULT NULL,
  user_code_expires_at 			timestamp NULL DEFAULT NULL,
  user_code_metadata 			blob,
  device_code_value 			blob,
  device_code_issued_at			timestamp NULL DEFAULT NULL,
  device_code_expires_at 		timestamp NULL DEFAULT NULL,
  device_code_metadata 			blob,
  PRIMARY KEY (id)
);

CREATE TABLE user.oauth2_registered_client
(
    id                            varchar(100)                        NOT NULL,
    client_id                     varchar(100)                        NOT NULL,
    client_id_issued_at           timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 varchar(200)                        NULL,
    client_secret_expires_at      timestamp                           NULL,
    client_name                   varchar(200)                        NOT NULL,
    client_authentication_methods varchar(1000)                       NOT NULL,
    authorization_grant_types     varchar(1000)                       NOT NULL,
    redirect_uris                 varchar(1000)                       NULL,
    scopes                        varchar(1000)                       NOT NULL,
    client_settings               varchar(2000)                       NOT NULL,
    token_settings                varchar(2000)                       NOT NULL,
	status 						  varchar(30) 						  DEFAULT 'LOCKED',
    PRIMARY KEY (id)
);

CREATE TABLE user.oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL,
    principal_name       varchar(200)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

-- used in tests that use HSQL
create table user.oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table user.oauth_client_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table user.oauth_access_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);

create table user.oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table user.oauth_code (
  code VARCHAR(256), authentication LONG VARBINARY
);

create table user.oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

-- customized oauth_client_details table
create table user.ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

CREATE TABLE user.SPRING_SESSION (
  PRIMARY_ID 				char(36) 	 NOT NULL,
  SESSION_ID 				char(36) 	 NOT NULL,
  CREATION_TIME 			bigint 		 NOT NULL,
  LAST_ACCESS_TIME 			bigint 		 NOT NULL,
  MAX_INACTIVE_INTERVAL 	int 		 NOT NULL,
  EXPIRY_TIME 				bigint 		 NOT NULL,
  PRINCIPAL_NAME 			varchar(100) DEFAULT NULL,
  PRIMARY KEY (PRIMARY_ID),
  UNIQUE KEY SPRING_SESSION_IX1 (SESSION_ID),
  KEY SPRING_SESSION_IX2 (EXPIRY_TIME),
  KEY SPRING_SESSION_IX3 (PRINCIPAL_NAME)
) ;

CREATE TABLE user.SPRING_SESSION_ATTRIBUTES (
  SESSION_PRIMARY_ID 		char(36) NOT NULL,
  ATTRIBUTE_NAME 			varchar(200) NOT NULL,
  ATTRIBUTE_BYTES 			blob NOT NULL,
  PRIMARY KEY (SESSION_PRIMARY_ID,ATTRIBUTE_NAME),
  CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY 
  (SESSION_PRIMARY_ID) REFERENCES user.SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
);

CREATE TABLE user.hibernate_sequence (
  next_val  BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY
);

insert into user.hibernate_sequence (next_val) values (1);
commit;



