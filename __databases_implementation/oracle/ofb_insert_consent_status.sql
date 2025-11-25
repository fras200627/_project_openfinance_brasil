insert into ofb.consents_status (consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(0, 'AWAITING_AUTHORISATION' ,1, 'start of permission', 'start of permission');
insert into ofb.consents_status (consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(1, 'AUTHORISED' ,1, 'permission confirmed', 'permission confirmed');
insert into ofb.consents_status (consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(2, 'REJECTED' ,1, 'permission rejected - approval time expired', 'permission rejected - approval time expired');
insert into ofb.consents_status (consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(2, 'REJECTED' ,2, 'permission rejected - consent expiration date', 'permission rejected - consent expiration date');
insert into ofb.consents_status (consentstatuscontrolid, status, statusstep, statusreason, statusdescription) 
VALUES(2, 'REJECTED' ,3, 'permission rejected - explicit revocation requested by client', 'permission rejected - explicit revocation requested by client');
/
COMMIT;
/
