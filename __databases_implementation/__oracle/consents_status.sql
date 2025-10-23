prompt Importing table OFB.CONSENTS_STATUS...
set feedback off
set define off

insert into OFB.CONSENTS_STATUS (CONSENTSTATUSID, CONSENTSTATUSCONTROLID, STATUS, STATUSSTEP, STATUSREASON, STATUSDESCRIPTION, CREATE_AT, MODIFY_AT, USER_CODE)
values (80, 0, 'AWAITING_AUTHORISATION', 1, 'start of permission', 'start of permission', '22/05/25 11:39:19,554 UTC', '22/05/25 11:39:19,554 UTC', 'USER_ADMIN');

insert into OFB.CONSENTS_STATUS (CONSENTSTATUSID, CONSENTSTATUSCONTROLID, STATUS, STATUSSTEP, STATUSREASON, STATUSDESCRIPTION, CREATE_AT, MODIFY_AT, USER_CODE)
values (81, 1, 'AUTHORISED', 1, 'permission confirmed', 'permission confirmed', '22/05/25 11:39:19,570 UTC', '22/05/25 11:39:19,570 UTC', 'USER_ADMIN');

insert into OFB.CONSENTS_STATUS (CONSENTSTATUSID, CONSENTSTATUSCONTROLID, STATUS, STATUSSTEP, STATUSREASON, STATUSDESCRIPTION, CREATE_AT, MODIFY_AT, USER_CODE)
values (82, 2, 'REJECTED', 1, 'permission rejected - approval time expired', 'permission rejected - approval time expired', '22/05/25 11:39:19,602 UTC', '22/05/25 11:39:19,602 UTC', 'USER_ADMIN');

insert into OFB.CONSENTS_STATUS (CONSENTSTATUSID, CONSENTSTATUSCONTROLID, STATUS, STATUSSTEP, STATUSREASON, STATUSDESCRIPTION, CREATE_AT, MODIFY_AT, USER_CODE)
values (83, 2, 'REJECTED', 2, 'permission rejected - consent expiration date', 'permission rejected - consent expiration date', '22/05/25 11:39:19,617 UTC', '22/05/25 11:39:19,617 UTC', 'USER_ADMIN');

insert into OFB.CONSENTS_STATUS (CONSENTSTATUSID, CONSENTSTATUSCONTROLID, STATUS, STATUSSTEP, STATUSREASON, STATUSDESCRIPTION, CREATE_AT, MODIFY_AT, USER_CODE)
values (84, 2, 'REJECTED', 3, 'permission rejected - explicit revocation requested by client', 'permission rejected - explicit revocation requested by client', '22/05/25 11:39:19,639 UTC', '22/05/25 11:39:19,639 UTC', 'USER_ADMIN');

insert into OFB.CONSENTS_STATUS (CONSENTSTATUSID, CONSENTSTATUSCONTROLID, STATUS, STATUSSTEP, STATUSREASON, STATUSDESCRIPTION, CREATE_AT, MODIFY_AT, USER_CODE)
values (85, 99, 'REQUEST_IN_PROGRESS', 0, 'request in progress - creation of a new consent in progress', 'request in progress - creation of a new consent in progress', '08/07/25 11:46:09,825 -03:00', '08/07/25 11:46:09,825 -03:00', 'USER_ADMIN');

insert into OFB.CONSENTS_STATUS (CONSENTSTATUSID, CONSENTSTATUSCONTROLID, STATUS, STATUSSTEP, STATUSREASON, STATUSDESCRIPTION, CREATE_AT, MODIFY_AT, USER_CODE)
values (86, 99, 'REQUEST_CANCELLED', 1, 'request cancelled - creation of a new consent cancelled', 'request cancelled - creation of a new consent cancelled', '08/07/25 11:48:01,658 -03:00', '08/07/25 11:48:01,658 -03:00', 'USER_ADMIN');

prompt Done.
