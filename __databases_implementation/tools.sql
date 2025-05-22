SELECT
'PERPF:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{3})([A-F0-9]{29})', '\1-\2') AS PersonalId,
'ACCPF:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{3})([A-F0-9]{29})', '\1-\2') AS PersonalAccount,
'ACCTX:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') AS TransactionId,
'CONPF:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') AS ConsentsPFId,
'urn:bancotcn:' || regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{32})', '\1') AS ConsentsId
FROM dual;

SELECT
rawtohex(sys_guid())
FROM dual;

SELECT 
CASE 
1000.0000 BETWEEN 0.0100 AND 1000000.000
THEN 'ok'
ELSE
  'nok'
END CASE FROM dual;
