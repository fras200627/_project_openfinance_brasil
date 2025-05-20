SELECT
regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{7})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{12})', '\1-\2-\3-\4-\5')
FROM dual;


insert into ofb.resources_account_personal_data
  (resourcetypeid, resourcestatusid, consentid, accountid)
values
  ('25cac914-d8ae-6002-b215-650a6215820d', 1, 'urn:bancotcn:B845D60ECFAB4614AAAF4E5D9494A14D', 'ACCPF:2F3-9686835924BECAAC80B2880595C10');

insert into ofb.resources_account_personal_data
  (resourcetypeid, resourcestatusid, consentid, accountid)
values
  ('25cac914-d8ae-6002-b215-650a6215820d', 1, 'urn:bancotcn:1086EBFAD5654048B18C126A142198B7', 'ACCPF:DD4-B1F73D8C84C09852040461FF21D1B');
  
