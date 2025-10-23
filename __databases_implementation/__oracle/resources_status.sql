prompt Importing table OFB.RESOURCES_STATUS...
set feedback off
set define off

insert into OFB.RESOURCES_STATUS (RESOURCESTATUSID, STATUS, SUMMARY, CREATE_AT, MODIFY_AT, USER_CODE)
values (1, 'Available', 'Disponível', '22/05/25 11:39:46,914 UTC', '22/05/25 11:39:46,914 UTC', 'USER_ADMIN');

insert into OFB.RESOURCES_STATUS (RESOURCESTATUSID, STATUS, SUMMARY, CREATE_AT, MODIFY_AT, USER_CODE)
values (2, 'Unavailable', 'Indisponível', '22/05/25 11:39:46,929 UTC', '22/05/25 11:39:46,929 UTC', 'USER_ADMIN');

insert into OFB.RESOURCES_STATUS (RESOURCESTATUSID, STATUS, SUMMARY, CREATE_AT, MODIFY_AT, USER_CODE)
values (3, 'Temporarily Unavailable', 'Temporariamente Indisponível', '22/05/25 11:39:46,950 UTC', '22/05/25 11:39:46,950 UTC', 'USER_ADMIN');

insert into OFB.RESOURCES_STATUS (RESOURCESTATUSID, STATUS, SUMMARY, CREATE_AT, MODIFY_AT, USER_CODE)
values (4, 'Pending Authorisation', 'Pendente de Autorização', '22/05/25 11:39:46,966 UTC', '22/05/25 11:39:46,966 UTC', 'USER_ADMIN');

prompt Done.
