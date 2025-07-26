-- Create table
create table OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
(
  consentid                    VARCHAR2(256) default 'urn:bancotcn:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') not null,
  requestdatetime              TIMESTAMP(3) WITH TIME ZONE default SYS_EXTRACT_UTC(SYSTIMESTAMP(3)) not null,
  previusexpirationdatetime    TIMESTAMP(3) WITH TIME ZONE not null,
  xfapicustomeripaddress       VARCHAR2(256) default '172.217.22.14' not null,
  xcustomeruseragent           VARCHAR2(256) default 'Mozilla/5.0 (iPhone14,6; U; CPU iPhone OS 15_4 like Mac OS X)' not null,
  expirationdatetime           TIMESTAMP(3) WITH TIME ZONE not null,
  expirationdatetimerequested  TIMESTAMP(3) WITH TIME ZONE not null,
  expirationdatetimeadjusted   TIMESTAMP(3) WITH TIME ZONE not null,
  expirationinmonths           NUMBER not null,
  expirationdateinfo           VARCHAR2(100) default 'INDETERMINADO' not null,
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
-- Create/Recreate primary, unique and foreign key constraints
alter table OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL
  add constraint CONSENTEXPIRATIONCONTROLFK1 foreign key (CONSENTID)
  references OFB.CONSENTS_PERSONAL_DATA (CONSENTID);

GRANT ALL ON OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL TO ofb, ofb_owner;
GRANT SELECT, INSERT, UPDATE, DELETE ON OFB.CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL TO ofb_user;

CREATE OR REPLACE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL AS
SELECT
  b.expirationcontrolid,
  a.consentid,
  to_char(a.creationdatetime,'YYYY-MM-DD"T"HH24:MI:SS"Z"') AS creationdatetime,
  a.status,
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

GRANT ALL ON OFB.VW_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL TO OFB, OFB_OWNER;
GRANT SELECT ON OFB.VW_CONSENTS_PERSONAL_DATA_EXPIRATION_CONTROL TO OFB_USER;
