ALTER SESSION SET "_ORACLE_SCRIPT"=true;


create sequence OFB.SQ_OFB_SYSTEM
minvalue 0
maxvalue 999999999999
start with 40
increment by 1
cache 20
cycle;
/

GRANT ALL ON OFB.SQ_OFB_SYSTEM TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.SQ_OFB_SYSTEM TO OFB_USER;
/

-- Create table
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
-- Add comments to the table 
comment on table OFB.RESOURCES_TYPES
  is 'Table System OFB to defined data resources';
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
-- Create/Recreate check constraints 
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

/

GRANT ALL ON OFB.RESOURCES_TYPES TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.RESOURCES_TYPES TO OFB_USER;
/

insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'RESOURCES' , 'AVAILABLE',
       'Resources', 'Tipo principal e obrigatório para todos os outros types', 'OFB_ADMIN');
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'CUSTOMER' , 'AVAILABLE',
       'Customers', 'Informações de dados cadastrais', 'OFB_ADMIN');       
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'ACCOUNT' , 'AVAILABLE',
       'Contas', 'Conta de depósito à vista, poupança ou pagamento pré-paga', 'OFB_ADMIN');
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'CREDIT_CARD_ACCOUNT' ,  'UNAVAILABLE',
       'Cartões', 'Conta de pagamento pós-paga (Cartão de Crédito)', 'OFB_ADMIN');
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'LOAN' ,  'UNAVAILABLE',
       'Empréstimos', 'Empréstimo', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'FINANCING' ,  'UNAVAILABLE',
       'Financiamentos', 'Financiamento', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'UNARRANGED_ACCOUNT_OVERDRAFT' ,  'UNAVAILABLE',
       'Cheque Especial', 'Cheque Especial', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'INVOICE_FINANCING' ,  'UNAVAILABLE',
       'Financiamentos de Faturas', 'Financiamento de Fatura', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'BANK_FIXED_INCOME' ,  'UNAVAILABLE',
       'Renda-Fixa', 'Renda Fixa Bancária', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'CREDIT_FIXED_INCOME' ,  'UNAVAILABLE',
       'Renda-Fixa Crédito', 'Renda Fixa Crédito', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'VARIABLE_INCOME' ,  'UNAVAILABLE',
       'Renda-Variável', 'Renda Variável', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'TREASURE_TITLE' ,  'UNAVAILABLE',
       'Tesouro Direto', 'Título do Tesouro Direto', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'FUND' ,  'UNAVAILABLE',
       'Fundos', 'Fundo de Investimento', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (resourceTYPEid, type, status, summary, description,  user_code)
VALUES (OFB.SQ_OFB_SYSTEM.NEXTVAL,'EXCHANGE',  'UNAVAILABLE',
       'Câmbio', 'Câmbio', 'OFB_ADMIN'); 
/

COMMIT;
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
/

GRANT ALL ON OFB.RESOURCES_PERMISSIONS TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.RESOURCES_PERMISSIONS TO OFB_USER;
/


insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 1, 'Dados Cadastrais PF', 1, 'CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 1, 'Dados Cadastrais PF', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 2, 'Informações complementares PF', 1, 'CUSTOMERS_PERSONAL_ADITTIONALINFO_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 2, 'Informações complementares PF', 0, 'RESOURCES_READ', 'false', 'Por recurso');


insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 3, 'Dados Cadastrais PJ', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 3, 'Dados Cadastrais PJ', 1, 'CUSTOMERS_BUSINESS_IDENTIFICATIONS_READ', 'true', 'Por recurso');

insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 4, 'Informações complementares PJ', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 4, 'Informações complementares PJ', 1, 'CUSTOMERS_BUSINESS_ADITTIONALINFO_READ', 'true', 'Por recurso');


insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 1, 'Saldos', 1, 'ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 1, 'Saldos', 2, 'ACCOUNTS_BALANCES_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 1, 'Saldos', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 2, 'Limites', 1, 'ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 2, 'Limites', 2, 'ACCOUNTS_OVERDRAFT_LIMITS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 2, 'Limites', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 3, 'Extratos', 1, 'ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 3, 'Extratos', 2, 'ACCOUNTS_TRANSACTIONS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 3, 'Extratos', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 1, 'Limites', 1, 'CREDIT_CARDS_ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 1, 'Limites', 2, 'CREDIT_CARDS_ACCOUNTS_LIMITS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 1, 'Limites', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 2, 'Transações', 1, 'CREDIT_CARDS_ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 2, 'Transações', 2, 'CREDIT_CARDS_ACCOUNTS_TRANSACTIONS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 2, 'Transações', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 3, 'Faturas', 1, 'CREDIT_CARDS_ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 3, 'Faturas', 2, 'CREDIT_CARDS_ACCOUNTS_BILLS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 3, 'Faturas', 3, 'CREDIT_CARDS_ACCOUNTS_BILLS_TRANSACTIONS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 3, 'Faturas', 0, 'RESOURCES_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Dados do Contrato', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Dados do Contrato', 1, 'LOANS_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Dados do Contrato', 2, 'LOANS_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Dados do Contrato', 3, 'LOANS_SCHEDULED_INSTALMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Dados do Contrato', 4, 'LOANS_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Dados do Contrato', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Dados do Contrato', 1, 'FINANCINGS_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Dados do Contrato', 2, 'FINANCINGS_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Dados do Contrato', 3, 'FINANCINGS_SCHEDULED_INSTALMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Dados do Contrato', 4, 'FINANCINGS_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Dados do Contrato', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Dados do Contrato', 1, 'UNARRANGED_ACCOUNTS_OVERDRAFT_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Dados do Contrato', 2, 'UNARRANGED_ACCOUNTS_OVERDRAFT_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Dados do Contrato', 3, 'UNARRANGED_ACCOUNTS_OVERDRAFT_SCHEDULED_INSTALMENTS_RE', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Dados do Contrato', 4, 'UNARRANGED_ACCOUNTS_OVERDRAFT_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Dados do Contrato', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Dados do Contrato', 1, 'INVOICE_FINANCINGS_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Dados do Contrato', 2, 'INVOICE_FINANCINGS_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Dados do Contrato', 3, 'INVOICE_FINANCINGS_SCHEDULED_INSTALMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Dados do Contrato', 4, 'INVOICE_FINANCINGS_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'BANK_FIXED_INCOME'), 5, 'Investimento', 1, 'Dados da Operação', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'BANK_FIXED_INCOME'), 5, 'Investimento', 1, 'Dados da Operação', 1, 'BANK_FIXED_INCOMES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_FIXED_INCOME'), 5, 'Investimento', 2, 'Dados da Operação', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_FIXED_INCOME'), 5, 'Investimento', 2, 'Dados da Operação', 1, 'CREDIT_FIXED_INCOMES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FUND'), 5, 'Investimento', 3, 'Dados da Operação', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FUND'), 5, 'Investimento', 3, 'Dados da Operação', 1, 'FUNDS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'VARIABLE_INCOME'), 5, 'Investimento', 4, 'Dados da Operação', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'VARIABLE_INCOME'), 5, 'Investimento', 4, 'Dados da Operação', 1, 'VARIABLE_INCOMES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'TREASURE_TITLE'), 5, 'Investimento', 5, 'Dados da Operação', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'TREASURE_TITLE'), 5, 'Investimento', 5, 'Dados da Operação', 1, 'TREASURE_TITLES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 1, 'Listar', 1, 'EXCHANGES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 1, 'Listar', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 2, 'Detalhes da Operação', 1, 'EXCHANGES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 2, 'Detalhes da Operação', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 3, 'Eventos', 1, 'EXCHANGES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcepermissionid, resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping) VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL,(SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 3, 'Eventos', 0, 'RESOURCES_READ', 'false', 'Por agrupamento de produtos');
/
COMMIT;
/

CREATE OR REPLACE VIEW OFB.VW_RESOURCES_TYPES AS
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
/

GRANT SELECT ON OFB.VW_RESOURCES_TYPES TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_RESOURCES_TYPES FOR OFB.VW_RESOURCES_TYPES;
GRANT ALL ON OFB.LIST_OF_RESOURCES_TYPES TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.LIST_OF_RESOURCES_TYPES TO OFB_USER;
/

create or replace view ofb.vw_resources_permissions as
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
/
GRANT SELECT ON ofb.vw_resources_permissions TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_RESOURCES_PERMISSIONS FOR ofb.vw_resources_permissions;
GRANT ALL ON OFB.LIST_OF_RESOURCES_PERMISSIONS TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.LIST_OF_RESOURCES_PERMISSIONS TO OFB_USER;
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
/

GRANT ALL ON OFB.CONSENTS_STATUS TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.CONSENTS_STATUS TO OFB_USER;
/

insert into ofb.consents_status (consentstatusid, consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL, 0, 'AWAITING_AUTHORISATION' ,1, 'start of permission', 'start of permission');
insert into ofb.consents_status (consentstatusid, consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL, 1, 'AUTHORISED' ,1, 'permission confirmed', 'permission confirmed');
insert into ofb.consents_status (consentstatusid, consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL, 2, 'REJECTED' ,1, 'permission rejected - approval time expired', 'permission rejected - approval time expired');
insert into ofb.consents_status (consentstatusid, consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL, 2, 'REJECTED' ,2, 'permission rejected - consent expiration date', 'permission rejected - consent expiration date');
insert into ofb.consents_status (consentstatusid, consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(OFB.SQ_OFB_SYSTEM.NEXTVAL, 2, 'REJECTED' ,3, 'permission rejected - explicit revocation requested by client', 'permission rejected - explicit revocation requested by client');
/
COMMIT;
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_STATUS AS
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
/

GRANT SELECT ON OFB.VW_CONSENTS_STATUS TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_CONSENTS_STATUS FOR OFB.VW_CONSENTS_STATUS;
GRANT ALL ON OFB.LIST_OF_CONSENTS_STATUS TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.LIST_OF_CONSENTS_STATUS TO OFB_USER;
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
/
GRANT ALL ON OFB.RESOURCES_STATUS TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.RESOURCES_STATUS TO OFB_USER;
/



insert into ofb.resources_status
  (resourcestatusid, status, summary)
values
  (1, 'Available', 'Disponível');

insert into ofb.resources_status
  (resourcestatusid, status, summary)
values
  (2, 'Unavailable', 'Indisponível');
  
insert into ofb.resources_status
  (resourcestatusid, status, summary)
values
  (3, 'Temporarily Unavailable', 'Temporariamente Indisponível');
  
insert into ofb.resources_status
  (resourcestatusid, status, summary)
values
  (4, 'Pending Authorisation', 'Pendente de Autorização');
/
COMMIT;
/

CREATE OR REPLACE VIEW OFB.VW_RESOURCES_STATUS AS
SELECT
  t.resourcestatusid,
  t.status,
  t.summary
from
  OFB.RESOURCES_STATUS t
ORDER BY
  t.resourcestatusid;
/

GRANT SELECT ON OFB.VW_RESOURCES_STATUS TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_RESOURCES_STATUS FOR OFB.VW_RESOURCES_STATUS;
GRANT ALL ON  OFB.LIST_OF_RESOURCES_STATUS TO OFB, OFB_OWNER;
GRANT SELECT ON  OFB.LIST_OF_RESOURCES_STATUS TO OFB_USER;
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
/

GRANT ALL ON OFB.CONSENTS_PERSONAL_DATA TO OFB, OFB_OWNER, SYSTEM;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.CONSENTS_PERSONAL_DATA TO OFB_USER;
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA AS
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
/


GRANT SELECT ON OFB.CONSENTS_PERSONAL_DATA TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_CONSENTS_PERSONAL_DATA FOR OFB.CONSENTS_PERSONAL_DATA;
GRANT ALL ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA TO OFB_USER;
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
/

GRANT ALL ON OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED TO OFB_USER;
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED AS
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
  c.permissioncategorygroup,
  c.permissioncategoryorder;
/

GRANT SELECT ON OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED FOR OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED;
GRANT ALL ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED TO OFB, OFB_OWNER;
GRANT SELECT ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED TO OFB_USER;
/

-- Create table
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
-- Add comments to the columns 
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.create_at
  is 'Record creation date';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.modify_at
  is 'Record of last modification of user data';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.user_code
  is 'User responsible for creating/modifying the resource';
-- Create/Recreate primary, unique and foreign key constraints 
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISED_PK primary key (CONSENTPERMISSIONAUTHORISEDID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255;
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDUNIQ1 unique (CONSENTID, PERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255;
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);
alter table OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED
  add constraint CONSENTSPERMISSIONSAUTHORISEDFK2 foreign key (PERMISSIONID)
  references OFB.RESOURCES_PERMISSIONS (RESOURCEPERMISSIONID);
/

GRANT ALL ON OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED TO OFB_USER;
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED AS
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
/

GRANT SELECT ON OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED FOR OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED;
GRANT ALL ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED TO OFB, OFB_OWNER;
GRANT SELECT ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED TO OFB_USER;
/

-- Create table
create table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
(
  consentresourceid NUMBER not null,
  resourceid        VARCHAR2(100) not null,
  resourceidsummary VARCHAR2(50) not null,
  resourcetype      CHAR(36) not null,
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
-- Add comments to the columns 
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.consentresourceid
  is 'Consents Resources PK';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.resourceid
  is 'Resource Id x Resources Type Reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.resourceidsummary
  is 'Resource Id summary infos';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED.resourcetype
  is 'Resource Type';
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
-- Create/Recreate primary, unique and foreign key constraints 
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
  add constraint CONSENTSRESOURCEUNIQ1 unique (CONSENTID, RESOURCETYPE, RESOURCEID)
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
-- Create/Recreate check constraints 
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
  add constraint CONSENTSRESOURCESCHECK1
  check (TRIM(resourceidsummary) IN
('accountId', 'creditCardAccountId', 'contractId', 'investmentId', 'operationId'));
/

GRANT ALL ON OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED TO OFB_USER;
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED AS
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
  ON a.resourcetype = b.resourcetypeid
  INNER JOIN OFB.RESOURCES_STATUS c
  ON a.RESOURCESTATUS = c.resourcestatusid
  INNER JOIN OFB.CONSENTS_PERSONAL_DATA d
  ON a.consentid = d.consentid
  INNER JOIN OFB.PERSONAL_DATA f
  ON d.personalid = f.personalid
ORDER BY
  f.cpfnumber, d.creationdatetime DESC, a.resourceid;
/

GRANT SELECT ON OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_CONFIRMED FOR OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED;
GRANT ALL ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_CONFIRMED TO OFB, OFB_OWNER;
GRANT SELECT ON  OFB.LIST_OF_CONSENTS_PERSONAL_DATA_PERMISSIONS_CONFIRMED TO OFB_USER;
/

-- Create table
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
-- Add comments to the columns 
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.consentresourcepermissionid
  is 'Consents Resources Confirmed Permissions Pk';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.consentresourceid
  is 'Consents Resources Confirmed reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.permissionid
  is 'Resources Permission Id reference';
-- Create/Recreate primary, unique and foreign key constraints 
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONPK primary key (CONSENTRESOURCEPERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255;
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONUNIQ1 unique (CONSENTRESOURCEID, PERMISSIONID)
  using index
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255;
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONFK1 foreign key (CONSENTRESOURCEID)
  references OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED (CONSENTRESOURCEID);
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS
  add constraint CONSENTRESOURCECONFIRMEDPERMISSIONFK2 foreign key (PERMISSIONID)
  references OFB.RESOURCES_PERMISSIONS (RESOURCEPERMISSIONID);
/

GRANT ALL ON OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS TO OFB_USER;
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
-- Create/Recreate check constraints 
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
/

-- Create table
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
-- Add comments to the columns 
comment on column OFB.ACCOUNT_PERSONAL_DATA.create_at
  is 'Data de criação';
comment on column OFB.ACCOUNT_PERSONAL_DATA.modify_at
  is 'Data da última modificação';
comment on column OFB.ACCOUNT_PERSONAL_DATA.user_code
  is 'Usuário executar da criação ou última alteração';
-- Create/Recreate primary, unique and foreign key constraints 
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
-- Create/Recreate check constraints 
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
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
-- Create/Recreate check constraints 
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
/

GRANT ALL ON OFB.PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.PERSONAL_DATA TO OFB_USER;
/

GRANT ALL ON OFB.ACCOUNT_PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.ACCOUNT_PERSONAL_DATA TO OFB_USER;
/

GRANT ALL ON OFB.ACCOUNT_PERSONAL_DATA_STATEMENT TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.ACCOUNT_PERSONAL_DATA_STATEMENT TO OFB_USER;
/

insert into ofb.personal_data
(civilname,socialname,birthdate,maritalstatuscode,sex,cpfnumber,
 address,districtname,townname,countrysubdivision,postcode,
 phonetype,phoneareacode,phonenumber,email)
VALUES (
'Sebastiana Analu Aparício',
'Sebastiana',
'1947-03-12',
'SOLTEIRO',
'FEMININO',
'73991016982',
'Rua Jonas F. S. Lima',
'Real Copagri',
'Teresina',
'PI',
'64007595',
'MOVEL',
'86',
'997675304',
'sebastiana-aparicio74@budsoncorporation.com');

insert into ofb.personal_data
(civilname,socialname,birthdate,maritalstatuscode,sex,cpfnumber,
 address,districtname,townname,countrysubdivision,postcode,
 phonetype,phoneareacode,phonenumber,email)
VALUES (
'Marina Alice Regina Peixoto',
'Marina',
'1954-03-12',
'SOLTEIRO',
'FEMININO',
'65046301601',
'Rua Vinte e Dois',
'Pedra 90',
'Cuiabá',
'MT',
'78099110',
'MOVEL',
'65',
'983126799',    
'marina-peixoto93@zigotto.com.br');

insert into ofb.personal_data
(civilname,socialname,birthdate,maritalstatuscode,sex,cpfnumber,
 address,districtname,townname,countrysubdivision,postcode,
 phonetype,phoneareacode,phonenumber,email)
VALUES (
'Isis Alessandra Nascimento',
'Isis',
'1983-01-16',
'SOLTEIRO',
'FEMININO',
'12816119483',
'Rua H 155',
'Cidade Vera Cruz',
'Aparecida de Goiânia',
'GO',
'74937540',
'MOVEL',
'62',
'993686217',
'isisalessandranascimento@2emesconstrutora.com.br');

insert into ofb.personal_data
(civilname,socialname,birthdate,maritalstatuscode,sex,cpfnumber,
 address,districtname,townname,countrysubdivision,postcode,
 phonetype,phoneareacode,phonenumber,email)
VALUES (
'Eduardo Lorenzo Bento Rezende',
'Eduardo',
'1964-05-04',
'CASADO',
'MASCULINO',
'54880884588',
'Rua Rondônia',
'Campina Grande',
'Cariacica',
'ES',
'29144421',
'MOVEL',
'27',
'986749223',
'eduardo_lorenzo_rezende@coreval.com.br');
/

COMMIT;
/

insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ('custpf-891-060B7A0804F00932444991EEB9C0B', '00100008', '0');

insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ('custpf-891-060B7A0804F00932444991EEB9C0B', '00100010', '1');
  
insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ('custpf-663-8EBE688BD40EABC77B2E7C0262AF1', '00100222', '8');
  
insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ('custpf-064-1CB6748DF49B4A076C490F3AAE158', '00105333', '2');
  
insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ('custpf-FA1-6B0F12BC243B8A02321139C27DB48', '00102121', '4');  
/

COMMIT;
/



insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-6B8-304F570324FD4B382822E5B297C20', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   1000.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-6B8-304F570324FD4B382822E5B297C20', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   23.3000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-6B8-304F570324FD4B382822E5B297C20', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   100.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-6B8-304F570324FD4B382822E5B297C20', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   102.2200);
 insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-6B8-304F570324FD4B382822E5B297C20', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   288.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-6B8-304F570324FD4B382822E5B297C20', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   500.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-6B8-304F570324FD4B382822E5B297C20', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   50.000);
/

COMMIT;
/

insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-A30-16695DCA649CE9A98C9DF3DF8D1BB', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   1000.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-A30-16695DCA649CE9A98C9DF3DF8D1BB', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   100.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-A30-16695DCA649CE9A98C9DF3DF8D1BB', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   500.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-A30-16695DCA649CE9A98C9DF3DF8D1BB', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   50.000);
/
COMMIT;
/

insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-5F0-45FF252DF4415A1EE5942A233C9BC', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   1000.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-5F0-45FF252DF4415A1EE5942A233C9BC', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   23.3000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-5F0-45FF252DF4415A1EE5942A233C9BC', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   100.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-5F0-45FF252DF4415A1EE5942A233C9BC', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   500.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-5F0-45FF252DF4415A1EE5942A233C9BC', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   50.000);
/
COMMIT;
/

insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   1000.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   23.3000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   100.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   500.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   50.000);
/
COMMIT;
/

insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-748-73B6110B24DA49EB077E744731354', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   1000.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-748-73B6110B24DA49EB077E744731354', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   23.3000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-748-73B6110B24DA49EB077E744731354', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   100.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-748-73B6110B24DA49EB077E744731354', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   102.2200);
 insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-748-73B6110B24DA49EB077E744731354', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   288.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-748-73B6110B24DA49EB077E744731354', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   500.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-748-73B6110B24DA49EB077E744731354', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   50.000);
/

COMMIT;
/

insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   1000.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   23.3000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'CREDITO', 'TX-CREDITO', 'PIX', 
   100.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   500.0000);
insert into ofb.account_personal_data_statement
  (accountid, completedauthorisedpaymenttype, 
   creditdebittype, transactionname, transactiontype, 
   transactionamount)
values
  ('accpf-32E-F0A6DC5444B4C9D58626410AAE4D5', 'TRANSACAO_EFETIVADA', 
   'DEBITO', 'TX-CREDITO', 'PIX', 
   50.000);
/
COMMIT;
/

UPDATE ofb.account_personal_data_statement t
SET t.transactiondatetime='30/04/25 15:00:00,000000'
WHERE
t.transactionamount = 50.0000;

UPDATE ofb.account_personal_data_statement t
SET t.transactiondatetime='02/04/25 15:00:00,000000'
WHERE
t.transactionamount = 23.3000;

UPDATE ofb.account_personal_data_statement t
SET t.transactiondatetime='01/05/25 15:00:00,000000'
WHERE
t.transactionamount = 100.0000;

UPDATE ofb.account_personal_data_statement t
SET t.transactiondatetime='05/05/25 15:00:00,000000'
WHERE
t.transactionamount = 288.0000;
/
COMMIT;
/

CREATE OR REPLACE VIEW OFB.VW_PERSONAL_DATA AS
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
/

create or replace view ofb.vw_account_personal_data as
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
/

CREATE OR REPLACE VIEW OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT AS
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
/

GRANT ALL ON OFB.PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.PERSONAL_DATA TO OFB_USER;
/

GRANT ALL ON OFB.ACCOUNT_PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.ACCOUNT_PERSONAL_DATA TO OFB_USER;
/

GRANT ALL ON OFB.ACCOUNT_PERSONAL_DATA_STATEMENT TO OFB, OFB_OWNER;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.ACCOUNT_PERSONAL_DATA_STATEMENT TO OFB_USER;
/

GRANT ALL ON OFB.VW_PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.VW_PERSONAL_DATA TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_PERSONAL_DATA FOR OFB.VW_PERSONAL_DATA;
GRANT ALL ON  OFB.LIST_OF_PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT ON  OFB.LIST_OF_PERSONAL_DATA TO OFB_USER;
/

GRANT ALL ON OFB.VW_ACCOUNT_PERSONAL_DATA TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.VW_ACCOUNT_PERSONAL_DATA TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_PERSONAL_ACCOUNT_DATA FOR OFB.VW_PERSONAL_ACCOUNT_DATA;
GRANT ALL ON OFB.LIST_OF_PERSONAL_ACCOUNT_DATA TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.LIST_OF_PERSONAL_ACCOUNT_DATA TO OFB_USER;
/

GRANT ALL ON OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT TO OFB_USER;
CREATE OR REPLACE SYNONYM OFB.LIST_OF_PERSONAL_ACCOUNT_DATA_STATEMENT FOR OFB.VW_PERSONAL_ACCOUNT_DATA_STATEMENT;
GRANT ALL ON OFB.LIST_OF_PERSONAL_ACCOUNT_DATA_STATEMENT TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.LIST_OF_PERSONAL_ACCOUNT_DATA_STATEMENT TO OFB_USER;
/


-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
/

-- Create table
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
-- Add comments to the columns 
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.create_at
  is 'Record creation date';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.modify_at
  is 'Record of last modification of user data';
comment on column OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED.user_code
  is 'User responsible for creating/modifying the resource';
-- Create/Recreate primary, unique and foreign key constraints 
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
/

-- Create table
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
-- Add comments to the columns 
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
-- Create/Recreate primary, unique and foreign key constraints 
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
-- Create/Recreate check constraints 
alter table OFB.CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED
  add constraint CONSENTSRESOURCESCHECK1
  check (TRIM(resourceidsummary) IN
('accountId', 'creditCardAccountId', 'contractId', 'investmentId', 'operationId'));
/

-- Create table
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
-- Add comments to the columns 
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.consentresourcepermissionid
  is 'Consents Resources Confirmed Permissions Pk';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.consentresourceid
  is 'Consents Resources Confirmed reference';
comment on column OFB.CONSENTS_PERSONAL_DATA_RESOURSES_CONFIRMED_PERMISSIONS.permissionid
  is 'Resources Permission Id reference';
-- Create/Recreate primary, unique and foreign key constraints 
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
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA AS
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
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED AS
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
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED AS
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
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSION_SUMMARY AS
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
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED AS
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
/

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_PERMISSIONS AS
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
/




