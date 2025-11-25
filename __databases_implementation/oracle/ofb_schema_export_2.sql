prompt PL/SQL Developer Export User Objects for user OFB@XE
prompt Created by fras200627 on quinta-feira, 22 de maio de 2025
set define off
spool ofb_schema_export_2.log

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
grant select, insert, update, delete, references, debug, read on OFB.VW_ACCOUNT_PERSONAL_DATA to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_ACCOUNT_PERSONAL_DATA to OFB_USER;


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
  to_char(t.transactiondatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS transactiondatetime,
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
grant select, insert, update, delete, references, debug, read on OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_ACCOUNT_PERSONAL_DATA_STATEMENT to OFB_USER;


prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA
prompt =======================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA AS
SELECT
  a.consentid,
  to_char(a.creationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS creationdatetime,
  a.consentstatusid,
  a.status,
  b.consentstatuscontrolid,
  b.statusstep,
  b.statusreason,
  to_char(a.statusupdatedatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS statusupdatedatetime,
  to_char(a.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS expirationdatetime,
  a.personalid,
  c.civilname,
  c.cpfnumber,
  a.loggeduseridentification,
  a.loggeduserdocumentrel,
  a.businessentityidentification,
  a.businessentitydocumentrel,
  a.awaitingauthby,
  to_char(a.awaitingauthstart,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS awaitingauthstart,
  to_char(a.awaitingauthend,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS awaitingauthend,
  a.awaitingauthaddicionalinfo,
  a.authorisedby,
  to_char(a.authorisedstart,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS authorisedstart,
  to_char(a.authorisedend,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS authorisedend,
  a.authorisedaddicionalinfo,
  a.rejectedby,
  a.rejectedcode,
  a.rejectedreason,
  a.rejectedaddiconalinfo,
  to_char(a.rejectedstartdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS rejectedstartdatetime,
  to_char(a.rejectedenddatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS rejectedenddatetime
from
  OFB.CONSENTS_PERSONAL_DATA a
  INNER JOIN OFB.CONSENTS_STATUS b
  ON a.consentstatusid = b.consentstatusid
  INNER JOIN OFB.PERSONAL_DATA c
  ON a.personalid = c.personalid
order by
  c.cpfnumber, a.consentid;
grant select, insert, update, delete, references, debug, read on OFB.VW_CONSENTS_PERSONAL_DATA to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_CONSENTS_PERSONAL_DATA to OFB_USER;


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
  to_char(b.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS expirationdatetime,
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
grant select, insert, update, delete, references, debug, read on OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED to OFB_USER;


prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED_SUMMARY
prompt ======================================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_AUTHORISED_SUMMARY AS
SELECT
  d.cpfnumber,
  a.consentid,
  d.civilname,
  b.status,
  to_char(b.creationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS creationdatetime,
  to_char( b.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS expirationdatetime,
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
prompt Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED
prompt =============================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED AS
SELECT
  e.cpfnumber,
  e.civilname          AS customer,
  b.status             AS consentstatus,
  b.expirationdatetime AS consentexpiration,
  to_char(b.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS expirationdatetime,
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
grant select, insert, update, delete, references, debug, read on OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED to OFB_USER;


prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED_SUMMARY
prompt =====================================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED_SUMMARY AS
SELECT
  d.cpfnumber,
  a.consentid,
  d.civilname,
  b.status,
  to_char(b.creationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS creationdatetime,
  to_char( b.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS expirationdatetime,
  c.permissioncategorygroup,
  c.permission
from
  OFB.CONSENTS_PERSONAL_DATA_PERMISSIONS_REQUESTED a
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
  to_char(d.creationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS CONSENTDATECREATION,
  d.status              AS CONSENTSTATUS,
  to_char(d.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"')  AS CONSENTEXPIRATION,
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
grant select, insert, update, delete, references, debug, read on OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED to OFB_USER;


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
  to_char(d.creationdatetime ,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS CONSENTDATECREATION,
  d.status              AS CONSENTSTATUS,
  to_char(d.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS CONSENTEXPIRATION,
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
grant select, insert, update, delete, references, debug, read on OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_PERMISSIONS to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_PERMISSIONS to OFB_USER;


prompt
prompt Creating view VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_PERMISSIONS_SUMMARY
prompt ===============================================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_PERMISSIONS_SUMMARY AS
SELECT
  f.cpfnumber           AS PERSONALCPF,
  a.CONSENTID,
  a.resourceid,
  b.type                AS RESOURCETYPE,
  a.resourceidsummary  ,
  c.status              AS RESOURCESTATUS,
  h.permission,

  to_char(d.creationdatetime ,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS CONSENTDATECREATION,
  d.status              AS CONSENTSTATUS,
  to_char(d.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS CONSENTEXPIRATION,
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
prompt Creating view VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_SUMMARY
prompt ===================================================================
prompt
CREATE OR REPLACE FORCE VIEW OFB.VW_CONSENTS_PERSONAL_DATA_RESOURCES_CONFIRMED_SUMMARY AS
SELECT
  f.cpfnumber           AS PERSONALCPF,
  a.CONSENTID,
  a.resourceid,
  b.type                AS RESOURCETYPE,
  a.resourceidsummary  ,
  c.status              AS RESOURCESTATUS,

  to_char(d.creationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"') AS CONSENTDATECREATION,
  d.status              AS CONSENTSTATUS,
  to_char(d.expirationdatetime,'YYYY-MM-DD"T"HH:MI:SS.FF3"Z"')  AS CONSENTEXPIRATION,
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
grant select, insert, update, delete, references, debug, read on OFB.VW_CONSENTS_STATUS to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_CONSENTS_STATUS to OFB_USER;


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
grant select, insert, update, delete, references, debug, read on OFB.VW_PERSONAL_DATA to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_PERSONAL_DATA to OFB_USER;


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
grant select, insert, update, delete, references, debug, read on OFB.VW_RESOURCES_PERMISSIONS to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_RESOURCES_PERMISSIONS to OFB_USER;


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
grant select, insert, update, delete, references, debug, read on OFB.VW_RESOURCES_STATUS to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_RESOURCES_STATUS to OFB_USER;


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
grant select, insert, update, delete, references, debug, read on OFB.VW_RESOURCES_TYPES to OFB_OWNER;
grant select, insert, update, delete on OFB.VW_RESOURCES_TYPES to OFB_USER;


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
     'automatic consents create',
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
      (consentid, permissionid)
    values
      (consents, reg_permissions.resourcepermissionid)
    RETURNING consentpermissionrequestedid INTO v_PermissionReg;
    COMMIT;
    dbms_output.put_line ('personalId: ' || v_PersonalId || ' RequestedPermissionId: ' || reg_permissions.resourcepermissionid ||
    ' id: ' || v_PermissionReg);

    insert into ofb.consents_personal_data_permissions_authorised
      (consentid, permissionid)
    values
      (consents, reg_permissions.resourcepermissionid)
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
      (resourceid, resourceidsummary, resourcetypeID, resourcestatus, consentid)
    values
      (reg_account.accountid, 'accountId', v_ResourceTypeId, v_ResourceStatusId, consents)
    RETURNING consentresourceid INTO v_ResourceConfirmedReg;
    COMMIT;

    dbms_output.put_line ('personalId: ' || reg_account.Personalid || ' accountId: ' || reg_account.accountid ||
    ' resourceConfirmed: ' || v_ResourceConfirmedReg);

    OPEN cur_Permissions;
    LOOP FETCH cur_Permissions INTO reg_permissions;
    EXIT WHEN cur_Permissions%NOTFOUND;
      IF (reg_permissions.resourcetype = 'ACCOUNT') THEN
        insert into ofb.consents_personal_data_resourses_confirmed_permissions
          (consentresourceid, permissionid)
        values
          (v_ResourceConfirmedReg, reg_permissions.resourcepermissionid)
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
