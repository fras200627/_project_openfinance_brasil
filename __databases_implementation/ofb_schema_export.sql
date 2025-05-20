prompt PL/SQL Developer Export User Objects for user OFB@XE
prompt Created by fras200627 on terça-feira, 20 de maio de 2025
set define off
spool ofb_schema_export.log

prompt
prompt Creating sequence SQ_OFB_SYSTEM
prompt ===============================
prompt
create sequence OFB.SQ_OFB_SYSTEM
minvalue 0
maxvalue 999999999999
start with 640
increment by 1
cache 20
cycle;

prompt
prompt Creating table PERSONAL_DATA
prompt ============================
prompt
create table OFB.PERSONAL_DATA
(
  personalid         CHAR(40) default 'custpf-' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{3})([A-F0-9]{29})', '\1-\2') not null,
  civilname          VARCHAR2(70) not null,
  socialname         VARCHAR2(70),
  birthdate          CHAR(10) not null,
  maritalstatuscode  VARCHAR2(30) not null,
  sex                VARCHAR2(30) not null,
  cpfnumber          CHAR(11) not null,
  address            VARCHAR2(150) not null,
  districtname       VARCHAR2(50) not null,
  townname           VARCHAR2(50) not null,
  countrysubdivision CHAR(2) not null,
  postcode           CHAR(8) not null,
  country            VARCHAR2(80) default 'BRASIL' not null,
  phonetype          VARCHAR2(10) not null,
  phoneareacode      VARCHAR2(3) not null,
  phonenumber        VARCHAR2(13) not null,
  email              VARCHAR2(320) not null,
  create_at          TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at          TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code          VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.PERSONAL_DATA.personalid
  is 'Personal Id (é o PK) assume como default um UUID';
comment on column OFB.PERSONAL_DATA.civilname
  is 'Nome';
comment on column OFB.PERSONAL_DATA.socialname
  is 'Nome Social';
comment on column OFB.PERSONAL_DATA.birthdate
  is 'Data de Nascimento';
comment on column OFB.PERSONAL_DATA.maritalstatuscode
  is 'Estado Civil';
comment on column OFB.PERSONAL_DATA.sex
  is 'Sexo';
comment on column OFB.PERSONAL_DATA.cpfnumber
  is 'CPF';
comment on column OFB.PERSONAL_DATA.address
  is 'Endereço';
comment on column OFB.PERSONAL_DATA.districtname
  is 'Bairro';
comment on column OFB.PERSONAL_DATA.townname
  is 'Cidade';
comment on column OFB.PERSONAL_DATA.countrysubdivision
  is 'Estado';
comment on column OFB.PERSONAL_DATA.postcode
  is 'CEP';
comment on column OFB.PERSONAL_DATA.country
  is 'Pais';
comment on column OFB.PERSONAL_DATA.phonetype
  is 'Tipo de Telefone';
comment on column OFB.PERSONAL_DATA.phoneareacode
  is 'Area ';
comment on column OFB.PERSONAL_DATA.phonenumber
  is 'Número do telefone';
comment on column OFB.PERSONAL_DATA.email
  is 'enail';
comment on column OFB.PERSONAL_DATA.create_at
  is 'Data de criação';
comment on column OFB.PERSONAL_DATA.modify_at
  is 'Data da última modificação';
comment on column OFB.PERSONAL_DATA.user_code
  is 'Usuário executar da criação ou última alteração';
alter table OFB.PERSONAL_DATA
  add constraint PERSONALDATAPK primary key (PERSONALID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.PERSONAL_DATA
  add constraint PERSONALDATAUNIQ1 unique (CPFNUMBER)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.PERSONAL_DATA
  add constraint PERSONAL_DATA_CHECK1
  check (UPPER(TRIM(maritalstatuscode)) IN (
  'SOLTEIRO',
  'CASADO',
  'VIUVO',
  'SEPARADO_JUDICIALMENTE',
  'DIVORCIADO',
  'UNIAO_ESTAVEL',
  'OUTRO'));
alter table OFB.PERSONAL_DATA
  add constraint PERSONAL_DATA_CHECK2
  check (UPPER(TRIM(countrysubdivision)) IN (
  'AC', 'AL', 'AP', 'AM', 'BA', 'CE',
  'DF', 'ES', 'GO', 'MA', 'MT', 'MS',
  'MG', 'PA', 'PB', 'PR', 'PE', 'PI',
  'RJ', 'RN', 'RS', 'RO', 'RR', 'SC',
  'SP', 'SE', 'TO'));
alter table OFB.PERSONAL_DATA
  add constraint PERSONAL_DATA_CHECK3
  check (UPPER(TRIM(sex)) IN (
  'FEMININO', 'MASCULINO', 'OUTRO'));
alter table OFB.PERSONAL_DATA
  add constraint PERSONAL_DATA_CHECK4
  check (UPPER(TRIM(phonetype)) IN (
  'FIXO', 'MOVEL', 'OUTRO'));

prompt
prompt Creating table ACCOUNT_PERSONAL_DATA
prompt ====================================
prompt
create table OFB.ACCOUNT_PERSONAL_DATA
(
  accountid                   CHAR(39) default 'accpf-' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{3})([A-F0-9]{29})', '\1-\2') not null,
  personalid                  CHAR(40) not null,
  accountstatus               VARCHAR2(30) default 'ATIVA' not null,
  accounttype                 VARCHAR2(50) default 'CONTA_DEPOSITO_A_VISTA' not null,
  accountsubtype              VARCHAR2(50) default 'INDIVIDUAL' not null,
  currency                    CHAR(3) default 'BRL' not null,
  brandname                   VARCHAR2(50) default 'BANCO TCN' not null,
  companycnpj                 CHAR(15) default '001222444000199' not null,
  compecode                   CHAR(3) default '333' not null,
  branchcode                  CHAR(4) default '0001' not null,
  accountnumber               CHAR(8) not null,
  accountcheckdigit           CHAR(1) not null,
  updateamountsdatetime       TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  availableamount             NUMBER(18,4) default 0 not null,
  blockedamount               NUMBER(18,4) default 0 not null,
  automaticallyinvestedamount NUMBER(18,4) default 0 not null,
  overdraftcontractedlimit    NUMBER(18,4) default 0 not null,
  overdraftusedlimit          NUMBER(18,4) default 0 not null,
  unarrangedoverdraftamount   NUMBER(18,4) default 0 not null,
  create_at                   TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at                   TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code                   VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.ACCOUNT_PERSONAL_DATA.create_at
  is 'Data de criação';
comment on column OFB.ACCOUNT_PERSONAL_DATA.modify_at
  is 'Data da última modificação';
comment on column OFB.ACCOUNT_PERSONAL_DATA.user_code
  is 'Usuário executar da criação ou última alteração';
alter table OFB.ACCOUNT_PERSONAL_DATA
  add constraint ACCOUNT_PK primary key (ACCOUNTID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.ACCOUNT_PERSONAL_DATA
  add constraint ACCOUNF_UNQ1 unique (COMPECODE, BRANCHCODE, ACCOUNTNUMBER)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.ACCOUNT_PERSONAL_DATA
  add constraint ACCOUNT_FK1 foreign key (PERSONALID)
  references OFB.PERSONAL_DATA (PERSONALID);
alter table OFB.ACCOUNT_PERSONAL_DATA
  add constraint ACCOUNT_CHECK0
  check (UPPER(TRIM(accountStatus)) IN (
  'ATIVA', 'ENCERRADA', 'BLOQUEADA'));
alter table OFB.ACCOUNT_PERSONAL_DATA
  add constraint ACCOUNT_CHECK1
  check (UPPER(TRIM(currency)) IN (
  'BRL', 'USD', 'EUR'));
alter table OFB.ACCOUNT_PERSONAL_DATA
  add constraint ACCOUNT_CHECK2
  check (UPPER(TRIM(accountType)) IN (
  'CONTA_DEPOSITO_A_VISTA', 'CONTA_POUPANCA', 'CONTA_PAGAMENTO_PRE_PAGA'));
alter table OFB.ACCOUNT_PERSONAL_DATA
  add constraint ACCOUNT_CHECK3
  check (UPPER(TRIM(accountSubtype)) IN (
  'INDIVIDUAL', 'CONJUNTA_SIMPLES', 'CONJUNTA_SOLIDARIA'));

prompt
prompt Creating table ACCOUNT_PERSONAL_DATA_STATEMENT
prompt ==============================================
prompt
create table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
(
  transactionid                  CHAR(39) default 'acctx-' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') not null,
  accountid                      CHAR(39) not null,
  referencetransactionid         CHAR(39),
  transactiondatetime            TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  completedauthorisedpaymenttype VARCHAR2(50) not null,
  creditdebittype                VARCHAR2(10) not null,
  transactionname                VARCHAR2(200) not null,
  transactiontype                VARCHAR2(50) not null,
  transactionamount              NUMBER(18,4) not null,
  transactioncurrency            CHAR(3) default 'BRL' not null,
  create_at                      TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at                      TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code                      VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.transactionid
  is 'Transaction Id';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.accountid
  is 'Account Id';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.transactiondatetime
  is 'Transation Date/Time';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.completedauthorisedpaymenttype
  is 'Status Final da Transação';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.creditdebittype
  is 'debito ou crédito';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.transactionname
  is 'Descrição da transação';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.transactiontype
  is 'Tipo de transação';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.transactionamount
  is 'Valor da transação';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.transactioncurrency
  is 'Moeda da transação';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.create_at
  is 'Data de criação';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.modify_at
  is 'Data da última modificação';
comment on column OFB.ACCOUNT_PERSONAL_DATA_STATEMENT.user_code
  is 'Usuário executar da criação ou última alteração';
alter table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
  add constraint TRANSACTION_PK primary key (TRANSACTIONID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
  add constraint TRANSACTION_FK1 foreign key (ACCOUNTID)
  references OFB.ACCOUNT_PERSONAL_DATA (ACCOUNTID);
alter table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
  add constraint TRANSACTION_CHECK1
  check ((TRANSACTIONAMOUNT BETWEEN 0.0100 AND 1000000.000));
alter table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
  add constraint TRANSACTION_CHECK2
  check (UPPER(TRIM(completedauthorisedpaymenttype)) IN
('TRANSACAO_EFETIVADA', 'LANCAMENTO_FUTURO', 'TRANSACAO_PROCESSANDO'));
alter table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
  add constraint TRANSACTION_CHECK3
  check (UPPER(TRIM(creditdebittype)) IN
('CREDITO', 'DEBITO'));
alter table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
  add constraint TRANSACTION_CHECK4
  check (UPPER(TRIM(transactiontype)) IN
('TED', 'DOC', 'PIX', 'TRANSFERENCIA_MESMA_INSTITUICAO',
 'BOLETO', 'CONVENIO_ARRECADACAO', 'PACOTE_TARIFA_SERVICOS',
 'TARIFA_SERVICOS_AVULSOS', 'FOLHA_PAGAMENTO', 'DEPOSITO',
 'SAQUE', 'CARTAO', 'ENCARGOS_JUROS_CHEQUE_ESPECIAL',
 'RENDIMENTO_APLIC_FINANCEIRA', 'PORTABILIDADE_SALARIO',
 'RESGATE_APLIC_FINANCEIRA', 'OPERACAO_CREDITO', 'OUTROS'));

prompt
prompt Creating table CONSENTS_STATUS
prompt ==============================
prompt
create table OFB.CONSENTS_STATUS
(
  consentstatusid        NUMBER not null,
  consentstatuscontrolid NUMBER not null,
  status                 VARCHAR2(200) not null,
  statusstep             NUMBER not null,
  statusreason           VARCHAR2(200) not null,
  statusdescription      VARCHAR2(3000) not null,
  create_at              TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at              TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code              VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.CONSENTS_STATUS.consentstatusid
  is 'Consent Status Id';
comment on column OFB.CONSENTS_STATUS.consentstatuscontrolid
  is 'Consents Status Control Id';
comment on column OFB.CONSENTS_STATUS.status
  is 'Consents Status';
comment on column OFB.CONSENTS_STATUS.statusstep
  is 'Consents Step Control Id';
comment on column OFB.CONSENTS_STATUS.statusreason
  is 'Consents Status Reason';
comment on column OFB.CONSENTS_STATUS.statusdescription
  is 'Consents Status Description';
comment on column OFB.CONSENTS_STATUS.create_at
  is 'Record creation date';
comment on column OFB.CONSENTS_STATUS.modify_at
  is 'Record of last modification of user data';
comment on column OFB.CONSENTS_STATUS.user_code
  is 'User responsible for creating/modifying the resource';
alter table OFB.CONSENTS_STATUS
  add constraint CONSENTSSTATUSPK primary key (CONSENTSTATUSID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_STATUS
  add constraint CONSENTSREASONUNIQ1 unique (CONSENTSTATUSID, STATUSREASON)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CONSENTS_PERSONAL_DATA
prompt =====================================
prompt
create table OFB.CONSENTS_PERSONAL_DATA
(
  consentid                    CHAR(45) default 'urn:bancotcn:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') not null,
  creationdatetime             TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  consentstatusid              NUMBER not null,
  status                       VARCHAR2(100) not null,
  statusupdatedatetime         TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  expirationdatetime           TIMESTAMP(6) WITH LOCAL TIME ZONE not null,
  personalid                   CHAR(40) not null,
  loggeduseridentification     VARCHAR2(30) not null,
  loggeduserdocumentrel        VARCHAR2(30) not null,
  businessentityidentification VARCHAR2(30) not null,
  businessentitydocumentrel    VARCHAR2(30) not null,
  awaitingauthby               VARCHAR2(30) not null,
  awaitingauthstart            TIMESTAMP(6) WITH LOCAL TIME ZONE not null,
  awaitingauthend              TIMESTAMP(6) WITH LOCAL TIME ZONE,
  awaitingauthaddicionalinfo   VARCHAR2(200) not null,
  authorisedby                 VARCHAR2(30),
  authorisedstart              TIMESTAMP(6) WITH LOCAL TIME ZONE,
  authorisedend                TIMESTAMP(6) WITH LOCAL TIME ZONE,
  authorisedaddicionalinfo     VARCHAR2(200),
  rejectedby                   VARCHAR2(30),
  rejectedcode                 VARCHAR2(100),
  rejectedreason               VARCHAR2(200),
  rejectedaddiconalinfo        VARCHAR2(200),
  rejectedstartdatetime        TIMESTAMP(6) WITH LOCAL TIME ZONE,
  rejectedenddatetime          TIMESTAMP(6) WITH LOCAL TIME ZONE,
  create_at                    TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at                    TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code                    VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.CONSENTS_PERSONAL_DATA.consentid
  is 'consent id';
comment on column OFB.CONSENTS_PERSONAL_DATA.creationdatetime
  is 'creation datetime';
comment on column OFB.CONSENTS_PERSONAL_DATA.consentstatusid
  is 'Consents Status id reference';
comment on column OFB.CONSENTS_PERSONAL_DATA.status
  is 'consents status';
comment on column OFB.CONSENTS_PERSONAL_DATA.statusupdatedatetime
  is 'Last update this consents';
comment on column OFB.CONSENTS_PERSONAL_DATA.expirationdatetime
  is 'Expiration Data this consents';
comment on column OFB.CONSENTS_PERSONAL_DATA.personalid
  is 'Personal id refrence';
comment on column OFB.CONSENTS_PERSONAL_DATA.loggeduseridentification
  is 'Log User Identification';
comment on column OFB.CONSENTS_PERSONAL_DATA.loggeduserdocumentrel
  is 'Log User document info';
comment on column OFB.CONSENTS_PERSONAL_DATA.businessentityidentification
  is 'Business information';
comment on column OFB.CONSENTS_PERSONAL_DATA.businessentitydocumentrel
  is 'Bussines document info';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthby
  is 'Awaiting step log by info';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthstart
  is 'Awaiting Date/Time of start';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthend
  is 'Awaiting Data/Time end ';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthaddicionalinfo
  is 'Awaitng step addicional info';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedby
  is 'Authorised User Info';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedstart
  is 'Authorised step start date/time';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedend
  is 'Authorised step end date-time';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedaddicionalinfo
  is 'Authorised addicional indo';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedby
  is 'Reject User info';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedcode
  is 'Reject code';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedreason
  is 'reject reason';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedaddiconalinfo
  is 'reject addicional ingo';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedstartdatetime
  is 'Reject step start date/time';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedenddatetime
  is 'Reject step end Date/time';
comment on column OFB.CONSENTS_PERSONAL_DATA.create_at
  is 'Record creation date';
comment on column OFB.CONSENTS_PERSONAL_DATA.modify_at
  is 'Record of last modification of user data';
comment on column OFB.CONSENTS_PERSONAL_DATA.user_code
  is 'User responsible for creating/modifying the resource';
alter table OFB.CONSENTS_PERSONAL_DATA
  add constraint CONSENTSPERSONALDATAPK1 primary key (CONSENTID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA
  add constraint CONSENTSPERSONALDATAFK1 foreign key (CONSENTSTATUSID)
  references OFB.CONSENTS_STATUS (CONSENTSTATUSID);
alter table OFB.CONSENTS_PERSONAL_DATA
  add constraint CONSENTSPERSONALDATAFK3 foreign key (PERSONALID)
  references OFB.PERSONAL_DATA (PERSONALID);

prompt
prompt Creating table RESOURCES_TYPES
prompt ==============================
prompt
create table OFB.RESOURCES_TYPES
(
  resourcetypeid NUMBER not null,
  type           VARCHAR2(100) not null,
  status         VARCHAR2(50) not null,
  summary        VARCHAR2(100) not null,
  description    VARCHAR2(300) not null,
  create_at      TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at      TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code      VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table OFB.RESOURCES_TYPES
  is 'Table System OFB to defined data resources';
comment on column OFB.RESOURCES_TYPES.resourcetypeid
  is 'Resource type PK';
comment on column OFB.RESOURCES_TYPES.type
  is 'Resource Type';
comment on column OFB.RESOURCES_TYPES.status
  is 'Resource Status';
comment on column OFB.RESOURCES_TYPES.summary
  is 'Resource Summary Type';
comment on column OFB.RESOURCES_TYPES.description
  is 'Resource Descritption Type';
comment on column OFB.RESOURCES_TYPES.create_at
  is 'Record creation date';
comment on column OFB.RESOURCES_TYPES.modify_at
  is 'Record of last modification of user data';
comment on column OFB.RESOURCES_TYPES.user_code
  is 'User responsible for creating/modifying the resource';
alter table OFB.RESOURCES_TYPES
  add primary key (RESOURCETYPEID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.RESOURCES_TYPES
  add unique (TYPE)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.RESOURCES_TYPES
  add constraint RESOURCES_STATUS_CHECK1
  check (UPPER(TRIM(STATUS)) IN ('AVAILABLE',
                              'UNAVAILABLE',
                              'TEMPORARILY_UNAVAILABLE',
                              'PENDING_AUTHORISATION'));
alter table OFB.RESOURCES_TYPES
  add constraint RESOURCES_TYPE_CHECK1
  check (UPPER(TRIM(TYPE)) IN ('RESOURCES', 'CUSTOMER', 'ACCOUNT',
                              'CREDIT_CARD_ACCOUNT',
                              'LOAN',
                              'FINANCING',
                              'UNARRANGED_ACCOUNT_OVERDRAFT',
                              'INVOICE_FINANCING',
                              'BANK_FIXED_INCOME',
                              'CREDIT_FIXED_INCOME',
                              'VARIABLE_INCOME',
                              'TREASURE_TITLE',
                              'FUND',
                              'EXCHANGE'));

prompt
prompt Creating table RESOURCES_PERMISSIONS
prompt ====================================
prompt
create table OFB.RESOURCES_PERMISSIONS
(
  resourcepermissionid    NUMBER not null,
  resourcetypeid          NUMBER not null,
  permissioncategoryid    NUMBER not null,
  permissioncategory      VARCHAR2(100) not null,
  permissioneventgroupid  NUMBER not null,
  permissioncategorygroup VARCHAR2(100) not null,
  permissioncategoryorder NUMBER not null,
  permission              VARCHAR2(100) not null,
  ispermissionevent       VARCHAR2(5) default 'false' not null,
  permissiongrouping      VARCHAR2(100) default 'Por Recurso' not null,
  create_at               TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at               TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code               VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.RESOURCES_PERMISSIONS.resourcepermissionid
  is 'Resource Permission Id';
comment on column OFB.RESOURCES_PERMISSIONS.resourcetypeid
  is 'Relacional Resources Types Id';
comment on column OFB.RESOURCES_PERMISSIONS.permissioncategoryid
  is 'Permission Category Id';
comment on column OFB.RESOURCES_PERMISSIONS.permissioncategory
  is 'Permission Category Description';
comment on column OFB.RESOURCES_PERMISSIONS.permissioneventgroupid
  is 'Category Group Id';
comment on column OFB.RESOURCES_PERMISSIONS.permissioncategorygroup
  is 'Permission Resource Group';
comment on column OFB.RESOURCES_PERMISSIONS.permissioncategoryorder
  is 'Category Order';
comment on column OFB.RESOURCES_PERMISSIONS.permission
  is 'Permission Event Key';
comment on column OFB.RESOURCES_PERMISSIONS.ispermissionevent
  is 'Permission Event Details';
comment on column OFB.RESOURCES_PERMISSIONS.permissiongrouping
  is 'Permission Resource Grouping';
comment on column OFB.RESOURCES_PERMISSIONS.create_at
  is 'Record creation date';
comment on column OFB.RESOURCES_PERMISSIONS.modify_at
  is 'Record of last modification of user data';
comment on column OFB.RESOURCES_PERMISSIONS.user_code
  is 'User responsible for creating/modifying the resource';
alter table OFB.RESOURCES_PERMISSIONS
  add constraint RESOURCEPEERMISSIONPK primary key (RESOURCEPERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.RESOURCES_PERMISSIONS
  add constraint RESOURCEPERMISSIONUNIQ1 unique (RESOURCETYPEID, PERMISSIONCATEGORY, PERMISSIONCATEGORYGROUP, PERMISSION)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.RESOURCES_PERMISSIONS
  add constraint RESOURCEPERMISSIONFK1 foreign key (RESOURCETYPEID)
  references OFB.RESOURCES_TYPES (RESOURCETYPEID);

prompt
prompt Creating table CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
prompt ============================================================
prompt
create table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
(
  consentpermissionauthorisedid NUMBER not null,
  consentid                     CHAR(45) not null,
  permissionid                  NUMBER not null,
  create_at                     TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at                     TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code                     VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.create_at
  is 'Record creation date';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.modify_at
  is 'Record of last modification of user data';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.user_code
  is 'User responsible for creating/modifying the resource';
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISED_PK primary key (CONSENTPERMISSIONAUTHORISEDID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDUNIQ1 unique (CONSENTID, PERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDFK2 foreign key (PERMISSIONID)
  references OFB.RESOURCES_PERMISSIONS (RESOURCEPERMISSIONID);

prompt
prompt Creating table CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
prompt ===========================================================
prompt
create table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
(
  consentpermissionrequestedid NUMBER not null,
  consentid                    CHAR(45) not null,
  permissionid                 NUMBER not null,
  create_at                    TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at                    TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code                    VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED.consentpermissionrequestedid
  is 'Consents Permission Id';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED.consentid
  is 'Consent id reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED.permissionid
  is 'Resource Permisson Id Reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED.create_at
  is 'Record creation date';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED.modify_at
  is 'Record of last modification of user data';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED.user_code
  is 'User responsible for creating/modifying the resource';
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
  add constraint CONSENTSPERMISSIONPK primary key (CONSENTPERMISSIONREQUESTEDID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
  add constraint CONSENTSPERMISSIONUINQ1 unique (CONSENTID, PERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
  add constraint CONSENTSPERMISSIONFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
  add constraint CONSENTSPERMISSIONFK2 foreign key (PERMISSIONID)
  references OFB.RESOURCES_PERMISSIONS (RESOURCEPERMISSIONID);

prompt
prompt Creating table RESOURCES_STATUS
prompt ===============================
prompt
create table OFB.RESOURCES_STATUS
(
  resourcestatusid NUMBER not null,
  status           VARCHAR2(30) not null,
  summary          VARCHAR2(200) not null,
  create_at        TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at        TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code        VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.RESOURCES_STATUS.resourcestatusid
  is 'Resources Status Id';
comment on column OFB.RESOURCES_STATUS.status
  is 'Possible Resources Status';
comment on column OFB.RESOURCES_STATUS.summary
  is 'Resources Status Description';
comment on column OFB.RESOURCES_STATUS.create_at
  is 'Record creation date';
comment on column OFB.RESOURCES_STATUS.modify_at
  is 'Record of last modification of user data';
comment on column OFB.RESOURCES_STATUS.user_code
  is 'User responsible for creating/modifying the resource';
alter table OFB.RESOURCES_STATUS
  add constraint RESOURCESSTATUSPK primary key (RESOURCESTATUSID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.RESOURCES_STATUS
  add constraint RESOURCESSTATUSUNIQ2 unique (STATUS)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
prompt =========================================================
prompt
create table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
(
  consentresourceid NUMBER not null,
  resourceid        VARCHAR2(100) not null,
  resourceidsummary VARCHAR2(50) not null,
  resourcetypeid    NUMBER not null,
  resourcestatus    NUMBER not null,
  consentid         CHAR(45) not null,
  create_at         TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at         TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code         VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.consentresourceid
  is 'Consents Resources PK';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.resourceid
  is 'Resource Id x Resources Type Reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.resourceidsummary
  is 'Resource Id summary infos';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.resourcetypeid
  is 'Resource Type id reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.resourcestatus
  is 'Resource Approval Status';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.consentid
  is 'ConsentId reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.create_at
  is 'Create registry date/time';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.modify_at
  is 'Last Date/Time update registry';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.user_code
  is 'Owner for create or last update this registry';
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
  add constraint CONSENTSRESOURCEPK primary key (CONSENTRESOURCEID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
  add constraint CONSENTSRESOURCEUNIQ1 unique (CONSENTID, RESOURCETYPEID, RESOURCEID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
  add constraint CONSENTSRESOURCEFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
  add constraint CONSENTSRESOURCEFK2 foreign key (RESOURCESTATUS)
  references OFB.RESOURCES_STATUS (RESOURCESTATUSID);
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
  add constraint CONSENTSRESOURCESCHECK1
  check (TRIM(resourceidsummary) IN
('accountId', 'creditCardAccountId', 'contractId', 'investmentId', 'operationId'));

prompt
prompt Creating table CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
prompt =====================================================================
prompt
create table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
(
  consentresourcepermissionid NUMBER not null,
  consentresourceid           NUMBER not null,
  permissionid                NUMBER not null,
  create_at                   TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  modify_at                   TIMESTAMP(6) WITH LOCAL TIME ZONE default CURRENT_TIMESTAMP not null,
  user_code                   VARCHAR2(20) default 'USER_ADMIN' not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.consentresourcepermissionid
  is 'Consents Resources Confirmed Permissions Pk';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.consentresourceid
  is 'Consents Resources Confirmed reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.permissionid
  is 'Resources Permission Id reference';
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONPK primary key (CONSENTRESOURCEPERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONUNIQ1 unique (CONSENTRESOURCEID, PERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONFK1 foreign key (CONSENTRESOURCEID)
  references OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED (CONSENTRESOURCEID);
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONFK2 foreign key (PERMISSIONID)
  references OFB.RESOURCES_PERMISSIONS (RESOURCEPERMISSIONID);

prompt
prompt Creating view VW_ACCOUNT_PERSONAL_DATA_STATEMENT
prompt ================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT AS
SELECT
  x.cpfnumber,
  t.transactionid,
  t.accountid,
  v.compecode,
  v.branchcode,
  v.accountnumber,
  v.accountcheckdigit,
  v.accounttype,
  v.personalid,
  x.civilname,
  t.referencetransactionid,
  t.transactiondatetime,
  t.completedauthorisedpaymenttype,
  t.creditdebittype,
  t.transactionname,
  t.transactiontype,
  t.transactionamount,
  t.transactioncurrency
from
  OFB.ACCOUNT_PERSONAL_DATA_STATEMENT t
  INNER JOIN OFB.ACCOUNT_PERSONAL_DATA v
  ON t.accountid = v.accountid
  INNER JOIN OFB.PERSONAL_DATA x
  ON v.personalid = x.personalid
ORDER BY
  x.cpfnumber, v.compecode,
  v.branchcode, v.accountnumber,
  t.transactiondatetime;

prompt
prompt Creating synonym LIST_OF_ACCOUNT_PERSONAL_DATA_STATEMENT
prompt ========================================================
prompt
create or replace synonym OFB.LIST_OF_ACCOUNT_PERSONAL_DATA_STATEMENT
  for OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT;

prompt
prompt Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA
prompt ===============================================
prompt
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA
  for OFB.CONSENTS_PERSONAL_DATA;

prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
prompt ==============================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED AS
SELECT
  e.cpfnumber,
  e.civilname          AS customer,
  a.consentid,
  b.status             AS consentstatus,
  b.expirationdatetime AS consentexpiration,
  d.type               AS resourcetype,
  c.permission,
  c.permissioncategory,
  c.permissioncategorygroup,
  a.permissionid,
  c.permissioncategoryorder
from
  OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED a
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA b
        ON a.consentid = b.consentid
  INNER JOIN OFB.RESOURCES_PERMISSIONS c
        ON a.permissionid = c.resourcepermissionid
  INNER JOIN OFB.RESOURCES_TYPES d
        ON c.resourceTYPEid = d.resourcetypeid
  INNER JOIN OFB.PERSONAL_DATA e
        ON b.personalid = e.personalid
ORDER BY
  e.cpfnumber,
  a.consentid,
  c.permissioncategorygroup,
  c.permissioncategoryorder;

prompt
prompt Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
prompt ======================================================================
prompt
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  for OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED;

prompt
prompt Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_CONFIRMED
prompt =====================================================================
prompt
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_CONFIRMED
  for OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED;

prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
prompt =============================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED AS
SELECT
  e.cpfnumber,
  e.civilname          AS customer,
  a.consentid,
  b.status             AS consentstatus,
  b.expirationdatetime AS consentexpiration,
  d.type               AS resourcetype,
  c.permission,
  c.permissioncategory,
  c.permissioncategorygroup,
  a.permissionid,
  c.permissioncategoryorder
from
  OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED a
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA b
        ON a.consentid = b.consentid
  INNER JOIN OFB.RESOURCES_PERMISSIONS c
        ON a.permissionid = c.resourcepermissionid
  INNER JOIN OFB.RESOURCES_TYPES d
        ON c.resourceTYPEid = d.resourcetypeid
  INNER JOIN OFB.PERSONAL_DATA e
        ON b.personalid = e.personalid
ORDER BY
  e.cpfnumber,
  a.consentid,
  c.permissioncategoryid,
  c.permissioncategorygroup,
  c.permissioncategoryorder;

prompt
prompt Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
prompt =====================================================================
prompt
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
  for OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED;

prompt
prompt Creating view VW_CONSENTS_STATUS
prompt ================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_STATUS AS
SELECT
  t.CONSENTSTATUSID,
  t.CONSENTSTATUSCONTROLID,
  t.STATUS,
  t.STATUSSTEP,
  t.STATUSREASON,
  t.STATUSDESCRIPTION
FROM
  OFB.CONSENTS_STATUS t
ORDER BY
  t.CONSENTSTATUSCONTROLID,
  t.STATUSSTEP;

prompt
prompt Creating synonym LIST_OF_CONSENTS_STATUS
prompt ========================================
prompt
create or replace synonym OFB.LIST_OF_CONSENTS_STATUS
  for OFB.VW_CONSENTS_STATUS;

prompt
prompt Creating synonym LIST_OF_PERSONAL_ACCOUNT_DATA
prompt ==============================================
prompt
create or replace synonym OFB.LIST_OF_PERSONAL_ACCOUNT_DATA
  for OFB.VW_PERSONAL_ACCOUNT_DATA;

prompt
prompt Creating synonym LIST_OF_PERSONAL_ACCOUNT_DATA_STATEMENT
prompt ========================================================
prompt
create or replace synonym OFB.LIST_OF_PERSONAL_ACCOUNT_DATA_STATEMENT
  for OFB.VW_PERSONAL_ACCOUNT_DATA_STATEMENT;

prompt
prompt Creating view VW_PERSONAL_DATA
prompt ==============================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_PERSONAL_DATA AS
SELECT
  cpfnumber,
  personalid,
  civilname,
  socialname,
  birthdate,
  maritalstatuscode,
  sex,
  address,
  districtname,
  townname,
  countrysubdivision,
  postcode,
  country,
  phonetype,
  phoneareacode,
  phonenumber,
  email
from
  OFB.PERSONAL_DATA t
ORDER BY
  t.cpfnumber ASC;

prompt
prompt Creating synonym LIST_OF_PERSONAL_DATA
prompt ======================================
prompt
create or replace synonym OFB.LIST_OF_PERSONAL_DATA
  for OFB.VW_PERSONAL_DATA;

prompt
prompt Creating view VW_RESOURCES_PERMISSIONS
prompt ======================================
prompt
create or replace force view ofb.vw_resources_permissions as
select
  resourcepermissionid,
  a.resourcetypeid,
  b.type  AS resourcetype,
  b.status AS resourcestatus,
  b.summary AS resourcesummary,
  permissioncategoryid,
  permissioncategory,
  permissioneventgroupid,
  permissioncategorygroup,
  permissioncategoryorder,
  permission,
  ispermissionevent,
  permissiongrouping
from
  OFB.RESOURCES_PERMISSIONS a
  INNER JOIN OFB.RESOURCES_TYPES b
  ON a.resourcetypeid = b.resourcetypeid
ORDER BY
  a.permissioncategoryid,
  a.permissioneventgroupid, a.permissioncategoryorder;

prompt
prompt Creating synonym LIST_OF_RESOURCES_PERMISSIONS
prompt ==============================================
prompt
create or replace synonym OFB.LIST_OF_RESOURCES_PERMISSIONS
  for OFB.VW_RESOURCES_PERMISSIONS;

prompt
prompt Creating view VW_RESOURCES_STATUS
prompt =================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_RESOURCES_STATUS AS
SELECT
  t.resourcestatusid,
  t.status,
  t.summary
from
  OFB.RESOURCES_STATUS t
ORDER BY
  t.resourcestatusid;

prompt
prompt Creating synonym LIST_OF_RESOURCES_STATUS
prompt =========================================
prompt
create or replace synonym OFB.LIST_OF_RESOURCES_STATUS
  for OFB.VW_RESOURCES_STATUS;

prompt
prompt Creating view VW_RESOURCES_TYPES
prompt ================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_RESOURCES_TYPES AS
SELECT
  t.resourceTYPEid   AS ResourceTYPEId,
  t.type             AS ResourceType,
  t.status           AS ResourceStatus,
  t.summary          AS Summary,
  t.description      AS Description
FROM
  OFB.RESOURCES_TYPES t
ORDER BY
  t.type;

prompt
prompt Creating synonym LIST_OF_RESOURCES_TYPES
prompt ========================================
prompt
create or replace synonym OFB.LIST_OF_RESOURCES_TYPES
  for OFB.VW_RESOURCES_TYPES;

prompt
prompt Creating view VW_ACCOUNT_PERSONAL_DATA
prompt ======================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_ACCOUNT_PERSONAL_DATA AS
SELECT
  v.cpfnumber,
  t.personalid,
  v.civilname,
  t.accountid,
  t.accountstatus,
  t.accounttype,
  t.accountsubtype,
  t.currency,
  t.brandname,
  t.companycnpj,
  t.compecode,
  t.branchcode,
  t.accountnumber,
  t.accountcheckdigit,
  t.updateamountsdatetime,
  t.availableamount,
  t.blockedamount,
  t.automaticallyinvestedamount,
  t.overdraftcontractedlimit,
  t.overdraftusedlimit,
  t.unarrangedoverdraftamount
from
  OFB.ACCOUNT_PERSONAL_DATA t
  INNER JOIN OFB.PERSONAL_DATA v
  ON t.personalid = v.personalid
ORDER BY
  v.cpfnumber, t.compecode, t.branchcode, t.accountnumber;

prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA
prompt =======================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA AS
SELECT
  a.consentid,
  a.creationdatetime,
  a.consentstatusid,
  a.status,
  b.consentstatuscontrolid,
  b.statusstep,
  b.statusreason,
  a.statusupdatedatetime,
  a.expirationdatetime,
  a.personalid,
  c.civilname,
  c.cpfnumber,
  a.loggeduseridentification,
  a.loggeduserdocumentrel,
  a.businessentityidentification,
  a.businessentitydocumentrel,
  a.awaitingauthby,
  a.awaitingauthstart,
  a.awaitingauthend,
  a.awaitingauthaddicionalinfo,
  a.authorisedby,
  a.authorisedstart,
  a.authorisedend,
  a.authorisedaddicionalinfo,
  a.rejectedby,
  a.rejectedcode,
  a.rejectedreason,
  a.rejectedaddiconalinfo,
  a.rejectedstartdatetime,
  a.rejectedenddatetime
from
  OFB.CONSENTS_PERSONAL_DATA a
  INNER JOIN OFB.CONSENTS_STATUS b
  ON a.consentstatusid = b.consentstatusid
  INNER JOIN OFB.PERSONAL_DATA c
  ON a.personalid = c.personalid
order by
  c.cpfnumber, a.consentid;

prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSION_SUMMARY
prompt ==========================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSION_SUMMARY AS
SELECT
  d.cpfnumber,
  a.consentid,
  d.civilname,
  b.status,
  b.creationdatetime,
  b.expirationdatetime,
  c.permissioncategorygroup,
  c.permission
from
  OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED a
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA b
  ON a.consentid = b.consentid
  INNER JOIN OFB.RESOURCES_PERMISSIONS c
  ON a.permissionid = c.resourcepermissionid
  INNER JOIN OFB.PERSONAL_DATA d
  ON b.personalid = d.personalid
ORDER BY
  d.cpfnumber,
  a.consentid,
  b.status,
  c.permissioncategorygroup,
  c.permissioncategoryorder;

prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
prompt ===========================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED AS
SELECT
  f.cpfnumber           AS PERSONALCPF,
  a.CONSENTRESOURCEID,
  a.resourceid,
  b.type                AS RESOURCETYPE,
  a.resourceidsummary  ,
  c.status              AS RESOURCESTATUS,
  a.CONSENTID,
  d.creationdatetime    AS CONSENTDATECREATION,
  d.status              AS CONSENTSTATUS,
  d.expirationdatetime  AS CONSENTEXPIRATION,
  d.personalid,
  f.civilname           AS PERSONALNAME
FROM
  OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED a
  INNER JOIN OFB.RESOURCES_TYPES b
  ON a.resourcetypeid = b.resourcetypeid
  INNER JOIN OFB.RESOURCES_STATUS c
  ON a.RESOURCESTATUS = c.resourcestatusid
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA d
  ON a.consentid = d.consentid
  INNER JOIN OFB.PERSONAL_DATA f
  ON d.personalid = f.personalid
ORDER BY
  f.cpfnumber, d.creationdatetime DESC, a.resourceid;

prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_PERMISSIONS
prompt =======================================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_PERMISSIONS AS
SELECT
  f.cpfnumber           AS PERSONALCPF,
  a.CONSENTRESOURCEID,
  a.resourceid,
  b.type                AS RESOURCETYPE,
  a.resourceidsummary  ,
  c.status              AS RESOURCESTATUS,
  g.permissionid,
  h.permission,
  a.CONSENTID,
  d.creationdatetime    AS CONSENTDATECREATION,
  d.status              AS CONSENTSTATUS,
  d.expirationdatetime  AS CONSENTEXPIRATION,
  d.personalid,
  f.civilname           AS PERSONALNAME
FROM
  OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED a
  INNER JOIN OFB.RESOURCES_TYPES b
  ON a.resourcetypeid = b.resourcetypeid
  INNER JOIN OFB.RESOURCES_STATUS c
  ON a.RESOURCESTATUS = c.resourcestatusid
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA d
  ON a.consentid = d.consentid
  INNER JOIN OFB.PERSONAL_DATA f
  ON d.personalid = f.personalid
  INNER JOIN ofb.consents_personal_data_resourses_confirmed_permissions g
  ON a.consentresourceid = g.consentresourceid
  INNER JOIN ofb.resources_permissions h
  ON g.permissionid = h.resourcepermissionid
ORDER BY
  f.cpfnumber, d.consentid, a.resourceid, h.permissioncategoryid,
  h.permissioneventgroupid, h.permissioncategoryorder;

prompt
prompt Creating procedure PRC_CREATE_CONSENTS
prompt ======================================
prompt
create or replace noneditionable procedure ofb.prc_create_consents
(identification in out varchar2, consentid out CHAR) 
IS
--
doc                    VARCHAR2(18):= identification;
consents               CHAR(45)    := '';
reg_customer           ofb.personal_data%ROWTYPE;
reg_account            ofb.vw_account_personal_data%ROWTYPE;
v_PersonalId           CHAR(40):= '';
reg_permissions        ofb.vw_resources_permissions%ROWTYPE;
v_PermissionReg        NUMBER:= 0;
v_ResourceStatusId     NUMBER:= 0;
v_ResourceTypeId       NUMBER:= 0;
v_ResourceConfirmedReg NUMBER:= 0;
v_ConsentRsourcePermissionId NUMBER:= 0;
v_ConsentsStatusId           NUMBER:= 0;
--
CURSOR cur_Accounts is
  select * from 
    OFB.VW_ACCOUNT_PERSONAL_DATA t
  WHERE
    t.personalid = v_PersonalId;
--
CURSOR cur_Permissions IS
SELECT * FROM
(
SELECT t.* FROM ofb.vw_resources_permissions t
WHERE 
(t.resourcetype = 'CUSTOMER'
AND t.permissioncategoryid = 1
AND t.permissioneventgroupid IN (1, 2))
UNION ALL
SELECT t.* FROM ofb.vw_resources_permissions t
WHERE 
(t.resourcetype = 'ACCOUNT'
AND t.permissioncategoryid = 2
AND t.permissioneventgroupid IN (1, 2, 3))
)
ORDER BY
permissioncategoryid, permissioneventgroupid, permissioncategoryorder;    
--
BEGIN

  -- Step 01: Create a consents ----------------------------------------------------
  SELECT * INTO reg_customer FROM ofb.personal_data WHERE cpfnumber = doc;
  v_PersonalId:= reg_customer.personalid;

  insert into ofb.consents_personal_data
    (consentstatusid, 
     status, 
     expirationdatetime, 
     personalid, 
     loggeduseridentification, loggeduserdocumentrel, 
     businessentityidentification, businessentitydocumentrel, 
     awaitingauthby, awaitingauthstart, awaitingauthend, 
     awaitingauthaddicionalinfo, 
     authorisedby, authorisedstart, authorisedend, 
     authorisedaddicionalinfo, 
     rejectedby, rejectedcode, rejectedreason, 
     rejectedaddiconalinfo, 
     rejectedstartdatetime, rejectedenddatetime)
  values
    ((select t.consentstatusid from OFB.CONSENTS_STATUS t WHERE t.status = 'AWAITING_AUTHORISATION'), 
     'AWAITING_AUTHORISATION', 
     CURRENT_TIMESTAMP, 
     v_PersonalId, 
     'USER', '00000000011', 
     'BANK', '000000000000000', 
     'USER', 
     CURRENT_TIMESTAMP, NULL, 
     'v_awaitingauthaddicionalinfo', 
     NULL, NULL, NULL, 
     NULL, 
     NULL, NULL, NULL, 
     NULL, 
     NULL, NULL)
  RETURNING consentid INTO consents;
  COMMIT;
  
  dbms_output.put_line ('personalId: ' || v_PersonalId || ' ConsentId: ' || consents);
  -- ---------------------------------------------------------------------------------

  -- Step 02: Create a Permissions Requested and Authorized --------------------------
  OPEN cur_Permissions;
  LOOP FETCH cur_Permissions INTO reg_permissions;
  EXIT WHEN cur_Permissions%NOTFOUND;
    insert into ofb.consents_personal_data_permissions_requested
      (consentpermissionrequestedid, consentid, permissionid)
    values
      (OFB.SQ_OFB_SYSTEM.NEXTVAL, consents, reg_permissions.resourcepermissionid)
    RETURNING consentpermissionrequestedid INTO v_PermissionReg;
    COMMIT;
    dbms_output.put_line ('personalId: ' || v_PersonalId || ' RequestedPermissionId: ' || reg_permissions.resourcepermissionid || 
    ' id: ' || v_PermissionReg);
        
    insert into ofb.consents_personal_data_permissions_authorised
      (consentpermissionauthorisedid, consentid, permissionid)
    values
      (OFB.SQ_OFB_SYSTEM.NEXTVAL, consents, reg_permissions.resourcepermissionid)
    RETURNING consentpermissionauthorisedid INTO v_PermissionReg;
    COMMIT;
    
    dbms_output.put_line ('personalId: ' || v_PersonalId || ' AutohorisedPermissionId: ' || reg_permissions.resourcepermissionid || 
    ' id: ' || v_PermissionReg);
  END LOOP;
  CLOSE cur_Permissions;
  -- ---------------------------------------------------------------------------------
  
  -- Step 03: Authorize Consents -----------------------------------------------------
  select t.consentstatusid INTO v_ConsentsStatusId from OFB.CONSENTS_STATUS t
  WHERE t.status = 'AUTHORISED';
  
  update ofb.consents_personal_data
     set consentstatusid = v_ConsentsStatusId,
         status = 'AUTHORISED',
         statusupdatedatetime = CURRENT_TIMESTAMP,
         awaitingauthend = CURRENT_TIMESTAMP,
         authorisedby = 'USER',
         authorisedend = CURRENT_TIMESTAMP,
         authorisedaddicionalinfo = 'AUTHORISED WITH PROCEDURE',
         modify_at = CURRENT_TIMESTAMP,
         user_code = 'USER_ADMIN'
  where consentid = consents;
  COMMIT;
  -- ---------------------------------------------------------------------------------  
  
  -- Step 04: Create a Resources Confirmed and Permissions ---------------------------
  select t.resourcestatusid INTO v_ResourceStatusId 
  from OFB.RESOURCES_STATUS t
  WHERE t.STATUS = 'Available';
  
  select t.resourcetypeid INTO v_ResourceTypeId
  from OFB.RESOURCES_TYPES t
  WHERE t.type = 'ACCOUNT';
  
  OPEN cur_Accounts;
  LOOP FETCH cur_Accounts INTO reg_account;
  EXIT WHEN cur_Accounts%NOTFOUND;
    insert into ofb.consents_personal_data_resources_confirmed
      (consentresourceid, resourceid, resourceidsummary, resourcetypeID, resourcestatus, consentid)
    values
      (OFB.SQ_OFB_SYSTEM.NEXTVAL, reg_account.accountid, 'accountId', v_ResourceTypeId, v_ResourceStatusId, consents)
    RETURNING consentresourceid INTO v_ResourceConfirmedReg;
    COMMIT;
  
    dbms_output.put_line ('personalId: ' || reg_account.Personalid || ' accountId: ' || reg_account.accountid || 
    ' resourceConfirmed: ' || v_ResourceConfirmedReg);
    
    OPEN cur_Permissions;
    LOOP FETCH cur_Permissions INTO reg_permissions;
    EXIT WHEN cur_Permissions%NOTFOUND;
      IF (reg_permissions.resourcetype = 'ACCOUNT') THEN
        insert into ofb.consents_personal_data_resourses_confirmed_permissions
          (consentresourcepermissionid, consentresourceid, permissionid)
        values
          (OFB.SQ_OFB_SYSTEM.NEXTVAL, v_ResourceConfirmedReg, reg_permissions.resourcepermissionid)
        RETURNING consentresourcepermissionid INTO v_ConsentRsourcePermissionId;
        COMMIT;
        
        dbms_output.put_line ('personalId: ' || reg_account.Personalid || ' accountId: ' || reg_account.accountid || 
        ' resourcePermissionConfirmed: ' || v_ConsentRsourcePermissionId);
      END IF;
    END LOOP;
    CLOSE cur_Permissions;
    
  END LOOP;
  CLOSE cur_Accounts;
  -- ---------------------------------------------------------------------------------

  
  consentid:=consents;
  
END;
/


prompt Done
spool off
set define on
