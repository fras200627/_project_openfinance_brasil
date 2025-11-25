--prompt  PL/SQL Developer Export User Objects for user OFB@XE
--prompt  Created by fras200627 on terça-feira, 25 de novembro de 2025
--set define off
--spool STEP_002_ORCL_OFB_CREATE_ALL_OBJECTS.log

--prompt 
--prompt  Creating sequence BATCH_JOB_EXECUTION_SEQ
--prompt  =========================================
--prompt 
create sequence OFB.BATCH_JOB_EXECUTION_SEQ
minvalue 0
maxvalue 9223372036854775807
start with 260
increment by 1
cache 20;

--prompt 
--prompt  Creating sequence BATCH_JOB_SEQ
--prompt  ===============================
--prompt 
create sequence OFB.BATCH_JOB_SEQ
minvalue 0
maxvalue 9223372036854775807
start with 220
increment by 1
cache 20;

--prompt 
--prompt  Creating sequence BATCH_STEP_EXECUTION_SEQ
--prompt  ==========================================
--prompt 
create sequence OFB.BATCH_STEP_EXECUTION_SEQ
minvalue 0
maxvalue 9223372036854775807
start with 220
increment by 1
cache 20;

--prompt 
--prompt  Creating sequence SQ_OFB_SYSTEM
--prompt  ===============================
--prompt 
create sequence OFB.SQ_OFB_SYSTEM
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20
cycle;

--prompt 
--prompt  Creating table PERSONAL_DATA
--prompt  ============================
--prompt 
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
  create_at          TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at          TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  user_code          VARCHAR2(20) default 'USER_ADMIN' not null,
  status             VARCHAR2(50) default 'ATIVO'
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
alter table OFB.PERSONAL_DATA
  add constraint PERSONAL_DATA_CHECK5
  check (UPPER(TRIM(STATUS)) IN ('ATIVO', 'INATIVO', 'BLOQUEADO', 'ENCERRADO'));

--prompt 
--prompt  Creating table ACCOUNT_PERSONAL_DATA
--prompt  ====================================
--prompt 
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
  updateamountsdatetime       TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  availableamount             NUMBER(18,4) default 0 not null,
  blockedamount               NUMBER(18,4) default 0 not null,
  automaticallyinvestedamount NUMBER(18,4) default 0 not null,
  overdraftcontractedlimit    NUMBER(18,4) default 0 not null,
  overdraftusedlimit          NUMBER(18,4) default 0 not null,
  unarrangedoverdraftamount   NUMBER(18,4) default 0 not null,
  create_at                   TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                   TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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

--prompt 
--prompt  Creating table ACCOUNT_PERSONAL_DATA_STATEMENT
--prompt  ==============================================
--prompt 
create table OFB.ACCOUNT_PERSONAL_DATA_STATEMENT
(
  transactionid                  CHAR(39) default 'acctx-' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') not null,
  accountid                      CHAR(39) not null,
  referencetransactionid         CHAR(39),
  transactiondatetime            TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  completedauthorisedpaymenttype VARCHAR2(50) not null,
  creditdebittype                VARCHAR2(10) not null,
  transactionname                VARCHAR2(200) not null,
  transactiontype                VARCHAR2(50) not null,
  transactionamount              NUMBER(18,4) not null,
  transactioncurrency            CHAR(3) default 'BRL' not null,
  create_at                      TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                      TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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

--prompt 
--prompt  Creating table BATCH_JOB_INSTANCE
--prompt  =================================
--prompt 
create table OFB.BATCH_JOB_INSTANCE
(
  job_instance_id NUMBER(19) not null,
  version         NUMBER(19),
  job_name        VARCHAR2(100 CHAR) not null,
  job_key         VARCHAR2(32 CHAR) not null
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
alter table OFB.BATCH_JOB_INSTANCE
  add primary key (JOB_INSTANCE_ID)
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
alter table OFB.BATCH_JOB_INSTANCE
  add constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
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

--prompt 
--prompt  Creating table BATCH_JOB_EXECUTION
--prompt  ==================================
--prompt 
create table OFB.BATCH_JOB_EXECUTION
(
  job_execution_id           NUMBER(19) not null,
  version                    NUMBER(19),
  job_instance_id            NUMBER(19) not null,
  create_time                TIMESTAMP(6) not null,
  start_time                 TIMESTAMP(6),
  end_time                   TIMESTAMP(6),
  status                     VARCHAR2(10 CHAR),
  exit_code                  VARCHAR2(2500 CHAR),
  exit_message               VARCHAR2(2500 CHAR),
  last_updated               TIMESTAMP(6),
  job_configuration_location VARCHAR2(2500 CHAR)
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
alter table OFB.BATCH_JOB_EXECUTION
  add primary key (JOB_EXECUTION_ID)
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
alter table OFB.BATCH_JOB_EXECUTION
  add constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
  references OFB.BATCH_JOB_INSTANCE (JOB_INSTANCE_ID);

--prompt 
--prompt  Creating table BATCH_JOB_EXECUTION_CONTEXT
--prompt  ==========================================
--prompt 
create table OFB.BATCH_JOB_EXECUTION_CONTEXT
(
  job_execution_id   NUMBER(19) not null,
  short_context      VARCHAR2(2500 CHAR) not null,
  serialized_context CLOB
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
alter table OFB.BATCH_JOB_EXECUTION_CONTEXT
  add primary key (JOB_EXECUTION_ID)
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
alter table OFB.BATCH_JOB_EXECUTION_CONTEXT
  add constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
  references OFB.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID);

--prompt 
--prompt  Creating table BATCH_JOB_EXECUTION_PARAMS
--prompt  =========================================
--prompt 
create table OFB.BATCH_JOB_EXECUTION_PARAMS
(
  job_execution_id NUMBER(19) not null,
  type_cd          VARCHAR2(6 CHAR) not null,
  key_name         VARCHAR2(100 CHAR) not null,
  string_val       VARCHAR2(250 CHAR),
  date_val         TIMESTAMP(6),
  long_val         NUMBER(19),
  double_val       NUMBER,
  identifying      CHAR(1) not null
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
alter table OFB.BATCH_JOB_EXECUTION_PARAMS
  add constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
  references OFB.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID);

--prompt 
--prompt  Creating table BATCH_STEP_EXECUTION
--prompt  ===================================
--prompt 
create table OFB.BATCH_STEP_EXECUTION
(
  step_execution_id  NUMBER(19) not null,
  version            NUMBER(19) not null,
  step_name          VARCHAR2(100 CHAR) not null,
  job_execution_id   NUMBER(19) not null,
  start_time         TIMESTAMP(6) not null,
  end_time           TIMESTAMP(6),
  status             VARCHAR2(10 CHAR),
  commit_count       NUMBER(19),
  read_count         NUMBER(19),
  filter_count       NUMBER(19),
  write_count        NUMBER(19),
  read_skip_count    NUMBER(19),
  write_skip_count   NUMBER(19),
  process_skip_count NUMBER(19),
  rollback_count     NUMBER(19),
  exit_code          VARCHAR2(2500 CHAR),
  exit_message       VARCHAR2(2500 CHAR),
  last_updated       TIMESTAMP(6)
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
alter table OFB.BATCH_STEP_EXECUTION
  add primary key (STEP_EXECUTION_ID)
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
alter table OFB.BATCH_STEP_EXECUTION
  add constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
  references OFB.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID);

--prompt 
--prompt  Creating table BATCH_STEP_EXECUTION_CONTEXT
--prompt  ===========================================
--prompt 
create table OFB.BATCH_STEP_EXECUTION_CONTEXT
(
  step_execution_id  NUMBER(19) not null,
  short_context      VARCHAR2(2500 CHAR) not null,
  serialized_context CLOB
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
alter table OFB.BATCH_STEP_EXECUTION_CONTEXT
  add primary key (STEP_EXECUTION_ID)
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
alter table OFB.BATCH_STEP_EXECUTION_CONTEXT
  add constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
  references OFB.BATCH_STEP_EXECUTION (STEP_EXECUTION_ID);

--prompt 
--prompt  Creating table CONSENTS_STATUS
--prompt  ==============================
--prompt 
create table OFB.CONSENTS_STATUS
(
  consentstatusid        NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  consentstatuscontrolid NUMBER not null,
  status                 VARCHAR2(200) not null,
  statusstep             NUMBER not null,
  statusreason           VARCHAR2(200) not null,
  statusdescription      VARCHAR2(3000) not null,
  create_at              TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at              TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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

--prompt 
--prompt  Creating table CONSENTS_PERSONAL_DATA
--prompt  =====================================
--prompt 
create table OFB.CONSENTS_PERSONAL_DATA
(
  consentid                  VARCHAR2(256) default 'urn:bancotcn:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') not null,
  creationdatetime           TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  consentstatusid            NUMBER not null,
  status                     VARCHAR2(100) not null,
  statusupdatedatetime       TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  expirationdatetime         TIMESTAMP(3) WITH TIME ZONE,
  expirationdateinfo         VARCHAR2(100) default 'INDETERMINADO' not null,
  personalid                 CHAR(40) not null,
  awaitingauthby             VARCHAR2(30) not null,
  awaitingauthstart          TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  awaitingauthend            TIMESTAMP(3) WITH TIME ZONE,
  awaitingauthadditionalinfo VARCHAR2(200),
  authorisedby               VARCHAR2(30),
  authorisedstart            TIMESTAMP(3) WITH TIME ZONE,
  authorisedend              TIMESTAMP(3) WITH TIME ZONE,
  authorisedadditionalinfo   VARCHAR2(200),
  rejectedby                 VARCHAR2(30),
  rejectedcode               VARCHAR2(100),
  rejectedreason             VARCHAR2(200),
  rejectedadditionalinfo     VARCHAR2(200),
  rejectedstartdatetime      TIMESTAMP(3) WITH TIME ZONE,
  rejectedenddatetime        TIMESTAMP(3) WITH TIME ZONE,
  create_at                  TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                  TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  user_code                  VARCHAR2(20) default 'USER_ADMIN' not null,
  cancelledby                VARCHAR2(30),
  cancelledreason            VARCHAR2(200),
  cancelledadditionalinfo    VARCHAR2(200),
  accesstokenauthorised      VARCHAR2(4000)
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
comment on column OFB.CONSENTS_PERSONAL_DATA.expirationdateinfo
  is 'Descriptive information about the expiration date: 03 MONTHS, 06 MONTHS, 12 MONTHS or INDETERMINATE.';
comment on column OFB.CONSENTS_PERSONAL_DATA.personalid
  is 'Personal id reference';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthby
  is 'Awaiting step log by info';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthstart
  is 'Awaiting Date/Time of start';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthend
  is 'Awaiting Data/Time end ';
comment on column OFB.CONSENTS_PERSONAL_DATA.awaitingauthadditionalinfo
  is 'Awaitng step additional info';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedby
  is 'Authorised User Info';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedstart
  is 'Authorised step start date/time';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedend
  is 'Authorised step end date-time';
comment on column OFB.CONSENTS_PERSONAL_DATA.authorisedadditionalinfo
  is 'Authorised additional indo';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedby
  is 'Reject User info';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedcode
  is 'Reject code';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedreason
  is 'reject reason';
comment on column OFB.CONSENTS_PERSONAL_DATA.rejectedadditionalinfo
  is 'reject additional ingo';
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
comment on column OFB.CONSENTS_PERSONAL_DATA.cancelledby
  is 'User responsible for cancel';
comment on column OFB.CONSENTS_PERSONAL_DATA.cancelledreason
  is 'cancel reason';
comment on column OFB.CONSENTS_PERSONAL_DATA.cancelledadditionalinfo
  is 'cancel additional info';
comment on column OFB.CONSENTS_PERSONAL_DATA.accesstokenauthorised
  is 'Access Token generarted after consents authorization';
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

--prompt 
--prompt  Creating table CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
--prompt  ========================================================
--prompt 
create table OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
(
  expirationcontrolid          NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  consentid                    VARCHAR2(256) default 'urn:bancotcn:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') not null,
  requestdatetime              TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  previusexpirationdatetime    TIMESTAMP(3) WITH TIME ZONE,
  xfapicustomeripaddress       VARCHAR2(256) default '172.217.22.14',
  xcustomeruseragent           VARCHAR2(256) default 'Mozilla/5.0 (iPhone14,6; U; CPU iPhone OS 15_4 like Mac OS X)',
  expirationdatetime           TIMESTAMP(3) WITH TIME ZONE,
  expirationdatetimerequested  TIMESTAMP(3) WITH TIME ZONE,
  expirationdatetimeadjusted   TIMESTAMP(3) WITH TIME ZONE,
  expirationinmonths           NUMBER,
  expirationdateinfo           VARCHAR2(100) default 'INDETERMINADO',
  loggeduseridentification     VARCHAR2(30) not null,
  loggeduserdocumentrel        VARCHAR2(30) not null,
  businessentityidentification VARCHAR2(30) not null,
  businessentitydocumentrel    VARCHAR2(30) not null,
  create_at                    TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                    TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  user_code                    VARCHAR2(20) default 'SystemOFBAdmin' not null
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
alter table OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
  add constraint EXPIRATIONCONTROLPK primary key (EXPIRATIONCONTROLID)
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
alter table OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
  add constraint CONSENTEXPIRATIONCONTROLFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);

--prompt 
--prompt  Creating table RESOURCES_TYPES
--prompt  ==============================
--prompt 
create table OFB.RESOURCES_TYPES
(
  resourcetypeid NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  type           VARCHAR2(100) not null,
  status         VARCHAR2(50) not null,
  summary        VARCHAR2(100) not null,
  description    VARCHAR2(300) not null,
  create_at      TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at      TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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

--prompt 
--prompt  Creating table RESOURCES_PERMISSIONS
--prompt  ====================================
--prompt 
create table OFB.RESOURCES_PERMISSIONS
(
  resourcepermissionid         NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  resourcetypeid               NUMBER not null,
  permissioncategoryid         NUMBER not null,
  permissioncategory           VARCHAR2(100) not null,
  permissioneventgroupid       NUMBER not null,
  permissioncategorygroup      VARCHAR2(100) not null,
  permissioncategoryorder      NUMBER not null,
  permission                   VARCHAR2(100) not null,
  ispermissionevent            VARCHAR2(5) default 'false' not null,
  permissiongrouping           VARCHAR2(100) default 'Por Recurso' not null,
  create_at                    TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                    TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  user_code                    VARCHAR2(20) default 'USER_ADMIN' not null,
  resourcestatus               VARCHAR2(30) not null,
  resourceisavailablebusiness  CHAR(1) not null,
  resourceisavailablepersonal  CHAR(1) not null,
  permissioncontrol            VARCHAR2(100) not null,
  permissioncontroldescription VARCHAR2(500) not null
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
comment on column OFB.RESOURCES_PERMISSIONS.resourcestatus
  is 'Resource Status';
comment on column OFB.RESOURCES_PERMISSIONS.resourceisavailablebusiness
  is 'Resource is valid for Customers Business';
comment on column OFB.RESOURCES_PERMISSIONS.resourceisavailablepersonal
  is 'Resourceis valid for Customers Personal';
comment on column OFB.RESOURCES_PERMISSIONS.permissioncontrol
  is 'Permission Control is Mandatory or not';
comment on column OFB.RESOURCES_PERMISSIONS.permissioncontroldescription
  is 'Permission Control description';
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

--prompt 
--prompt  Creating table CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
--prompt  ============================================================
--prompt 
create table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
(
  consentpermissionauthorisedid NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  consentid                     VARCHAR2(256) not null,
  permissionid                  NUMBER not null,
  create_at                     TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                     TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDFK2 foreign key (PERMISSIONID)
  references OFB.RESOURCES_PERMISSIONS (RESOURCEPERMISSIONID);

--prompt 
--prompt  Creating table CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
--prompt  ===========================================================
--prompt 
create table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
(
  consentpermissionrequestedid NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  consentid                    VARCHAR2(256) not null,
  permissionid                 NUMBER not null,
  create_at                    TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                    TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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

--prompt 
--prompt  Creating table RESOURCES_STATUS
--prompt  ===============================
--prompt 
create table OFB.RESOURCES_STATUS
(
  resourcestatusid NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  status           VARCHAR2(30) not null,
  summary          VARCHAR2(200) not null,
  create_at        TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at        TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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

--prompt 
--prompt  Creating table CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
--prompt  ==========================================================
--prompt 
create table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
(
  consentresourceid NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  resourceid        VARCHAR2(100) not null,
  resourceidsummary VARCHAR2(50) not null,
  resourcetypeid    NUMBER not null,
  resourcestatus    NUMBER not null,
  consentid         VARCHAR2(256) not null,
  create_at         TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at         TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.consentresourceid
  is 'Consents Resources PK';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.resourceid
  is 'Resource Id x Resources Type Reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.resourceidsummary
  is 'Resource Id summary infos';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.resourcetypeid
  is 'Resource Type id reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.resourcestatus
  is 'Resource Approval Status';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.consentid
  is 'ConsentId reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.create_at
  is 'Create registry date/time';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.modify_at
  is 'Last Date/Time update registry';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED.user_code
  is 'Owner for create or last update this registry';
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
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
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
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
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
  add constraint CONSENTSRESOURCEFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
  add constraint CONSENTSRESOURCEFK2 foreign key (RESOURCESTATUS)
  references OFB.RESOURCES_STATUS (RESOURCESTATUSID);
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
  add constraint CONSENTSRESOURCESCHECK1
  check (TRIM(resourceidsummary) IN
('customerId','accountId', 'creditCardAccountId', 'contractId', 'investmentId', 'operationId'));

--prompt 
--prompt  Creating table CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS
--prompt  ======================================================================
--prompt 
create table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS
(
  consentresourcepermissionid NUMBER default OFB.SQ_OFB_SYSTEM.NEXTVAL not null,
  consentresourceid           NUMBER not null,
  permissionid                NUMBER not null,
  create_at                   TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  modify_at                   TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
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
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS.consentresourcepermissionid
  is 'Consents Resources Confirmed Permissions Pk';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS.consentresourceid
  is 'Consents Resources Confirmed reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS.permissionid
  is 'Resources Permission Id reference';
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS
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
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS
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
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONFK1 foreign key (CONSENTRESOURCEID)
  references OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED (CONSENTRESOURCEID);
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONFK2 foreign key (PERMISSIONID)
  references OFB.RESOURCES_PERMISSIONS (RESOURCEPERMISSIONID);

--prompt 
--prompt  Creating table TESTE
--prompt  ====================
--prompt 
create table OFB.TESTE
(
  teste NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;

--prompt 
--prompt  Creating view VW_ACCOUNT_PERSONAL_DATA
--prompt  ======================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_ACCOUNT_PERSONAL_DATA AS
SELECT
  t.rowid AS ID,
  v.cpfnumber,
  v.status AS personalStatus,
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
  to_char(t.updateamountsdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"')    AS updateamountsdatetime,
  to_char(t.availableamount, 'FM999999999999990.0000')             AS availableamount,
  to_char(t.blockedamount, 'FM999999999999990.0000')               AS blockedamount,
  to_char(t.automaticallyinvestedamount, 'FM999999999999990.0000') AS automaticallyinvestedamount,
  to_char(t.overdraftcontractedlimit, 'FM999999999999990.0000')    AS overdraftcontractedlimit,
  to_char(t.overdraftusedlimit, 'FM999999999999990.0000')          AS overdraftusedlimit,
  to_char(t.unarrangedoverdraftamount, 'FM999999999999990.0000')   AS unarrangedoverdraftamount
from
  OFB.ACCOUNT_PERSONAL_DATA t
  INNER JOIN OFB.PERSONAL_DATA v
  ON t.personalid = v.personalid
ORDER BY
  v.cpfnumber, t.compecode, t.branchcode, t.accountnumber;

--prompt 
--prompt  Creating synonym LIST_OF_ACCOUNT_PERSONAL_DATA
--prompt  ==============================================
--prompt 
create or replace synonym OFB.LIST_OF_ACCOUNT_PERSONAL_DATA
  for OFB.VW_ACCOUNT_PERSONAL_DATA;

--prompt 
--prompt  Creating view VW_ACCOUNT_PERSONAL_DATA_STATEMENT
--prompt  ================================================
--prompt 
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
  to_char(t.transactiondatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS transactiondatetime,
  t.transactiondatetime AS txdatetime,
  t.completedauthorisedpaymenttype,
  t.creditdebittype,
  t.transactionname,
  t.transactiontype,
  to_char(t.transactionamount, 'FM999999999999990.0000')    AS transactionamount,
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

--prompt 
--prompt  Creating synonym LIST_OF_ACCOUNT_PERSONAL_DATA_STATEMENT
--prompt  ========================================================
--prompt 
create or replace synonym OFB.LIST_OF_ACCOUNT_PERSONAL_DATA_STATEMENT
  for OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT;

--prompt 
--prompt  Creating view VW_CONSENTS_PERSONAL_DATA
--prompt  =======================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA AS
SELECT z."CONSENTID",z."EXPIRATIONCONTROLID",z."PERSONALID",z."CIVILNAME",z."CPFNUMBER",z."CREATIONDATETIME",z."CONSENTSTATUSID",z."STATUS",z."ACCESSTOKENAUTHORISED",z."CONSENTSTATUSCONTROLID",z."STATUSSTEP",z."STATUSREASON",z."STATUSUPDATEDATETIME",z."EXPIRATIONDATETIME",z."EXPIRATIONINMONTHS",z."EXPIRATIONDATEINFO",z."LOGGEDUSERIDENTIFICATION",z."LOGGEDUSERDOCUMENTREL",z."BUSINESSENTITYIDENTIFICATION",z."BUSINESSENTITYDOCUMENTREL",z."AWAITINGAUTHBY",z."AWAITINGAUTHSTART",z."AWAITINGAUTHEND",z."AWAITINGAUTHADDITIONALINFO",z."AUTHORISEDBY",z."AUTHORISEDSTART",z."AUTHORISEDEND",z."AUTHORISEDADDITIONALINFO",z."REJECTEDBY",z."REJECTEDCODE",z."REJECTEDREASON",z."REJECTEDADDITIONALINFO",z."REJECTEDSTARTDATETIME",z."REJECTEDENDDATETIME",z."CANCELLEDBY",z."CANCELLEDREASON",z."CANCELLEDADDITIONALINFO" FROM (
SELECT
  a.consentid,
  d.expirationcontrolid,
  a.personalid,
  c.civilname,
  c.cpfnumber,
  --
  to_char(a.creationdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"')     AS creationdatetime,
  a.consentstatusid,
  a.status,
  a.accesstokenauthorised,
  b.consentstatuscontrolid,
  b.statusstep,
  b.statusreason,
  --
  to_char(a.statusupdatedatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS statusupdatedatetime,
  to_char(a.expirationdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"')   AS expirationdatetime,
  d.expirationinmonths,
  a.expirationdateinfo,
  --
  d.loggeduseridentification,
  d.loggeduserdocumentrel,
  d.businessentityidentification,
  d.businessentitydocumentrel,
  --
  a.awaitingauthby,
  to_char(a.awaitingauthstart,'YYYY-MM-DD"T"HH24:MI:SS"Z"')    AS awaitingauthstart,
  to_char(a.awaitingauthend,'YYYY-MM-DD"T"HH24:MI:SS"Z"')      AS awaitingauthend,
  a.awaitingauthadditionalinfo,
  --
  a.authorisedby,
  to_char(a.authorisedstart,'YYYY-MM-DD"T"HH24:MI:SS"Z"')      AS authorisedstart,
  to_char(a.authorisedend,'YYYY-MM-DD"T"HH24:MI:SS"Z"')        AS authorisedend,
  a.authorisedadditionalinfo,
  --
  a.rejectedby,
  a.rejectedcode,
  a.rejectedreason,
  a.rejectedadditionalinfo,
  to_char(a.rejectedstartdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS rejectedstartdatetime,
  to_char(a.rejectedenddatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"')   AS rejectedenddatetime,
  --
  a.cancelledby,
  a.cancelledreason,
  a.cancelledadditionalinfo
from
  OFB.CONSENTS_PERSONAL_DATA a
  INNER JOIN OFB.CONSENTS_STATUS b
  ON a.consentstatusid = b.consentstatusid
  INNER JOIN OFB.PERSONAL_DATA c
  ON a.personalid = c.personalid
  INNER JOIN OFB.consents_personal_data_expiration_control d
  ON d.consentid = a.consentid) z
WHERE
  z.expirationcontrolid = (SELECT MAX(x.expirationcontrolid)
                           FROM
                           OFB.consents_personal_data_expiration_control x
                           WHERE
                           x.consentid = z.consentid)
order by
  z.cpfnumber, z.consentid
;

--prompt 
--prompt  Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA
--prompt  ===============================================
--prompt 
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA
  for OFB.VW_CONSENTS_PERSONAL_DATA;

--prompt 
--prompt  Creating view VW_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
--prompt  ==========================================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL AS
SELECT
  b.expirationcontrolid,
  a.consentid,
  to_char(a.creationdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS creationdatetime,
  a.status,
  b.create_at AS createat,
  to_char(b.requestdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS requestdatetime,
  to_char(b.previusexpirationdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS previusexpirationdatetime,
  b.xfapicustomeripaddress,
  b.xcustomeruseragent,
  to_char(b.expirationdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS expirationdatetime,
  to_char(b.expirationdatetimerequested,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS expirationdatetimerequested,
  to_char(b.expirationdatetimeadjusted,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS expirationdatetimeadjusted,
  b.expirationinmonths,
  b.expirationdateinfo,
  b.loggeduseridentification,
  b.loggeduserdocumentrel,
  b.businessentityidentification,
  b.businessentitydocumentrel
from
  OFB.CONSENTS_PERSONAL_DATA a
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL b
  ON a.consentid = b.consentid
order by
  a.consentid, b.requestdatetime DESC;

--prompt 
--prompt  Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
--prompt  ==================================================================
--prompt 
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
  for OFB.VW_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL;

--prompt 
--prompt  Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
--prompt  ==============================================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED AS
SELECT
  A.CONSENTPERMISSIONAUTHORISEDID AS ID,
  E.CPFNUMBER                     AS CUSTOMERCPFNUMBER,
  E.CIVILNAME                     AS CUSTOMERNAME,
  E.PERSONALID,
  A.CONSENTID,
  B.STATUS                        AS CONSENTSTATUS,
  TO_CHAR(B.EXPIRATIONDATETIME,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS EXPIRATIONDATETIME,
  D.TYPE                          AS RESOURCETYPE,
  C.RESOURCESTATUS,
  A.PERMISSIONID,
  C.PERMISSIONCATEGORYORDER,
  C.PERMISSION,
  C.PERMISSIONCATEGORY,
  C.PERMISSIONCATEGORYGROUP
FROM
  OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED A
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA B
  ON A.CONSENTID = B.CONSENTID
  INNER JOIN OFB.RESOURCES_PERMISSIONS C
  ON A.PERMISSIONID = C.RESOURCEPERMISSIONID
  INNER JOIN OFB.RESOURCES_TYPES D
  ON C.RESOURCETYPEID = D.RESOURCETYPEID
  INNER JOIN OFB.PERSONAL_DATA E
  ON B.PERSONALID = E.PERSONALID
WHERE
  C.RESOURCESTATUS = 'AVAILABLE'
ORDER BY
  E.CPFNUMBER,
  A.CONSENTID,
  C.PERMISSIONCATEGORYGROUP,
  C.PERMISSIONCATEGORYORDER;

--prompt 
--prompt  Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
--prompt  ======================================================================
--prompt 
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  for OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED;

--prompt 
--prompt  Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
--prompt  =============================================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED AS
SELECT
  a.consentpermissionrequestedid,
  e.cpfnumber,
  a.consentid,
  e.civilname          AS customer,
  b.status             AS consentstatus,
  b.expirationdatetime AS consentexpiration,
  to_char(b.expirationdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS expirationdatetime,
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

--prompt 
--prompt  Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
--prompt  =====================================================================
--prompt 
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
  for OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED;

--prompt 
--prompt  Creating view VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
--prompt  ============================================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED AS
SELECT
  A.CONSENTRESOURCEID           AS ID,
  A.CONSENTID,
  TO_CHAR(D.CREATIONDATETIME,
  'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS CONSENTDATECREATION,
  D.STATUS                      AS CONSENTSTATUS,
  TO_CHAR(D.EXPIRATIONDATETIME,
  'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS CONSENTEXPIRATION,
  F.PERSONALID,
  F.CIVILNAME           AS PERSONALNAME,
  F.CPFNUMBER,
  F.BIRTHDATE,
  F.SEX,
  F.ADDRESS,
  F.DISTRICTNAME,
  F.TOWNNAME,
  F.COUNTRYSUBDIVISION,
  F.POSTCODE,
  F.COUNTRY,
  F.PHONETYPE,
  F.PHONEAREACODE,
  F.PHONENUMBER,
  F.EMAIL,
  A.RESOURCEID,
  B.TYPE                AS RESOURCETYPE,
  A.RESOURCEIDSUMMARY,
  C.STATUS              AS RESOURCESTATUS,
  I.ACCOUNTTYPE,
  I.ACCOUNTSUBTYPE,
  I.ACCOUNTSTATUS,
  I.BRANDNAME           AS ACCOUNTBRANDNAME,
  I.COMPANYCNPJ         AS ACCOUNTCOMPANYCNPJ,
  I.COMPECODE           AS ACCOUNTCOMPECODE,
  I.BRANCHCODE          AS ACCOUNTBRANCHCODE,
  I.ACCOUNTNUMBER,
  I.ACCOUNTCHECKDIGIT
FROM
  OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED A
  INNER JOIN OFB.RESOURCES_TYPES B
  ON A.RESOURCETYPEID = B.RESOURCETYPEID
  INNER JOIN OFB.RESOURCES_STATUS C
  ON A.RESOURCESTATUS = C.RESOURCESTATUSID
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA D
  ON A.CONSENTID = D.CONSENTID
  INNER JOIN OFB.PERSONAL_DATA F
  ON D.PERSONALID = F.PERSONALID
  LEFT JOIN OFB.ACCOUNT_PERSONAL_DATA I
  ON A.RESOURCEID = I.ACCOUNTID
ORDER BY
  F.CPFNUMBER, D.CREATIONDATETIME DESC, A.RESOURCEID;

--prompt 
--prompt  Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
--prompt  ====================================================================
--prompt 
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED
  for OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED;

--prompt 
--prompt  Creating view VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_PERMISSIONS
--prompt  ========================================================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_PERMISSIONS AS
SELECT
  G.CONSENTRESOURCEPERMISSIONID AS ID,
  F.CPFNUMBER                   AS PERSONALCPF,
  A.CONSENTID,
  D.STATUS                      AS CONSENTSTATUS,
  TO_CHAR(D.CREATIONDATETIME,
  'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS CONSENTDATECREATION,
  TO_CHAR(D.EXPIRATIONDATETIME,
  'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS CONSENTEXPIRATION,
  F.PERSONALID,
  F.CIVILNAME                   AS PERSONALNAME,
  F.CPFNUMBER,
  F.BIRTHDATE,
  F.SEX,
  F.ADDRESS,
  F.DISTRICTNAME,
  F.TOWNNAME,
  F.COUNTRYSUBDIVISION,
  F.POSTCODE,
  F.COUNTRY,
  F.PHONETYPE,
  F.PHONEAREACODE,
  F.PHONENUMBER,
  F.EMAIL,
  A.RESOURCEID,
  B.TYPE                        AS RESOURCETYPE,
  I.ACCOUNTTYPE,
  I.ACCOUNTSUBTYPE,
  I.ACCOUNTSTATUS,
  I.BRANDNAME                   AS ACCOUNTBRANDNAME,
  I.COMPANYCNPJ                 AS ACCOUNTCOMPANYCNPJ,
  I.COMPECODE                   AS ACCOUNTCOMPECODE,
  I.BRANCHCODE                  AS ACCOUNTBRANCHCODE,
  I.ACCOUNTNUMBER,
  I.ACCOUNTCHECKDIGIT,
  I.CURRENCY,
  A.RESOURCEIDSUMMARY,
  C.STATUS                      AS RESOURCESTATUS,
  G.PERMISSIONID,
  H.PERMISSION
FROM
  OFB.CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED A
  INNER JOIN OFB.RESOURCES_TYPES B
  ON A.RESOURCETYPEID = B.RESOURCETYPEID
  INNER JOIN OFB.RESOURCES_STATUS C
  ON A.RESOURCESTATUS = C.RESOURCESTATUSID
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA D
  ON A.CONSENTID = D.CONSENTID
  INNER JOIN OFB.PERSONAL_DATA F
  ON D.PERSONALID = F.PERSONALID
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA_RESOURSES_AUTHORISED_PERMISSIONS G
  ON A.CONSENTRESOURCEID = G.CONSENTRESOURCEID
  INNER JOIN OFB.RESOURCES_PERMISSIONS H
  ON G.PERMISSIONID = H.RESOURCEPERMISSIONID
  LEFT JOIN OFB.ACCOUNT_PERSONAL_DATA I
  ON A.RESOURCEID = I.ACCOUNTID
ORDER BY
  F.CPFNUMBER, D.CONSENTID, A.RESOURCEID, H.PERMISSIONCATEGORYID,
  H.PERMISSIONEVENTGROUPID, H.PERMISSIONCATEGORYORDER;

--prompt 
--prompt  Creating synonym LIST_OF_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_PERMISSIONS
--prompt  ================================================================================
--prompt 
create or replace synonym OFB.LIST_OF_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_PERMISSIONS
  for OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_AUTHORISED_PERMISSIONS;

--prompt 
--prompt  Creating view VW_CONSENTS_STATUS
--prompt  ================================
--prompt 
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

--prompt 
--prompt  Creating synonym LIST_OF_CONSENTS_STATUS
--prompt  ========================================
--prompt 
create or replace synonym OFB.LIST_OF_CONSENTS_STATUS
  for OFB.VW_CONSENTS_STATUS;

--prompt 
--prompt  Creating view VW_PERSONAL_DATA
--prompt  ==============================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_PERSONAL_DATA AS
SELECT
  t.CPFNUMBER,
  t.PERSONALID,
  t.STATUS,
  t.CIVILNAME,
  t.SOCIALNAME,
  t.BIRTHDATE,
  t.MARITALSTATUSCODE,
  t.SEX,
  t.ADDRESS,
  t.DISTRICTNAME,
  t.TOWNNAME,
  t.COUNTRYSUBDIVISION,
  t.POSTCODE,
  t.COUNTRY,
  t.PHONETYPE,
  t.PHONEAREACODE,
  t.PHONENUMBER,
  t.EMAIL,
  to_char(t.modify_at,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS LASTUPDATE
FROM
  OFB.PERSONAL_DATA t
ORDER BY
  T.CPFNUMBER ASC;

--prompt 
--prompt  Creating synonym LIST_OF_PERSONAL_DATA
--prompt  ======================================
--prompt 
create or replace synonym OFB.LIST_OF_PERSONAL_DATA
  for OFB.VW_PERSONAL_DATA;

--prompt 
--prompt  Creating view VW_RESOURCES_PERMISSIONS
--prompt  ======================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_RESOURCES_PERMISSIONS AS
SELECT a."RESOURCEPERMISSIONID",a."RESOURCETYPEID",a."RESOURCETYPE",a."RESOURCESUMMARY",a."RESOURCESTATUS",a."PERMISSION",a."PERMISSIONID",a."PERMISSIONCATEGORY",a."CONTROL",a."CONTROLDESCRIPTION",a."PERMISSIONGROUPING",a."PERMISSIONCATEGORYGROUP",a."QUALIFIEDFORPJ",a."QUALIFIEDFORPF" FROM (
  SELECT
    resourcepermissionid,
    a.resourcetypeid,
    b.type                       AS resourcetype,
    b.summary                    AS resourcesummary,
    a.resourcestatus             AS resourcestatus,
    resourcepermissionid         AS permissionid,
    permission,
    permissioncategory,
    permissioncontrol            AS control,
    permissioncontroldescription AS controldescription,
    permissiongrouping,
    permissioncategorygroup,
    a.resourceisavailablebusiness AS qualifiedForPJ,
    a.resourceisavailablepersonal AS qualifiedForPF
  FROM
    OFB.RESOURCES_PERMISSIONS a
    INNER JOIN OFB.RESOURCES_TYPES b
    ON a.resourcetypeid = b.resourcetypeid
  ORDER BY
    a.permissioncategoryid,
    a.permissioneventgroupid, a.permissioncategoryorder) a;

--prompt 
--prompt  Creating synonym LIST_OF_RESOURCES_PERMISSIONS
--prompt  ==============================================
--prompt 
create or replace synonym OFB.LIST_OF_RESOURCES_PERMISSIONS
  for OFB.VW_RESOURCES_PERMISSIONS;

--prompt 
--prompt  Creating view VW_RESOURCES_STATUS
--prompt  =================================
--prompt 
CREATE OR REPLACE FORCE VIEW OFB.VW_RESOURCES_STATUS AS
SELECT
  t.resourcestatusid,
  t.status,
  t.summary
from
  OFB.RESOURCES_STATUS t
ORDER BY
  t.resourcestatusid;

--prompt 
--prompt  Creating synonym LIST_OF_RESOURCES_STATUS
--prompt  =========================================
--prompt 
create or replace synonym OFB.LIST_OF_RESOURCES_STATUS
  for OFB.VW_RESOURCES_STATUS;

--prompt 
--prompt  Creating view VW_RESOURCES_TYPES
--prompt  ================================
--prompt 
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

--prompt 
--prompt  Creating synonym LIST_OF_RESOURCES_TYPES
--prompt  ========================================
--prompt 
create or replace synonym OFB.LIST_OF_RESOURCES_TYPES
  for OFB.VW_RESOURCES_TYPES;

--prompt 
--prompt  Creating trigger TRG_ACCOUNT_PERSONAL_DATA
--prompt  ==========================================
--prompt 
CREATE OR REPLACE NONEDITIONABLE TRIGGER OFB.TRG_ACCOUNT_PERSONAL_DATA
  AFTER UPDATE 
  OF ACCOUNTSTATUS
  ON OFB.ACCOUNT_PERSONAL_DATA 
  FOR EACH ROW
DECLARE
resourceStatus NUMBER:= 0;
--PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
--ACCOUNT_STATUS      RESOURCE STATUS
------------------------------------------------
--ATIVA               1  Available
--ENCERRADA           2  Unavailable
--BLOQUEADA           3  Temporarily Unavailable
--                    4  Pending Authorisation
------------------------------------------------

/*  SELECT a.resourcestatus INTO resourceStatus 
  FROM OFB.consents_personal_data_resources_confirmed a
  WHERE
  a.resourceid = :old.ACCOUNTID;
  
  IF resourceStatus = 4 THEN
    RETURN;
  ELSE*/
    IF :NEW.ACCOUNTSTATUS = 'ATIVA' THEN
      UPDATE  OFB.consents_personal_data_resources_authorised a
      SET a.resourcestatus = 1
       WHERE a.resourceid = :OLD.ACCOUNTID
      AND a.resourcestatus != 4;
      COMMIT;
    ELSIF :NEW.ACCOUNTSTATUS = 'ENCERRADA' THEN
      UPDATE  OFB.consents_personal_data_resources_authorised a
      SET a.resourcestatus = 2
      WHERE a.resourceid = :OLD.ACCOUNTID
      AND a.resourcestatus != 4;
      COMMIT;
    ELSIF :NEW.ACCOUNTSTATUS = 'BLOQUEADA' THEN
      UPDATE  OFB.consents_personal_data_resources_authorised a
      SET a.resourcestatus = 3
      WHERE a.resourceid = :OLD.ACCOUNTID
      AND a.resourcestatus != 4;
      COMMIT;
    END IF;
--  END IF;
  
EXCEPTION
  WHEN OTHERS THEN
    RETURN;
END TRG_ACCOUNT_PERSONAL_DATA;
/


--prompt  Done
--spool off
--set define on
