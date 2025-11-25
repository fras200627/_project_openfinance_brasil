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
