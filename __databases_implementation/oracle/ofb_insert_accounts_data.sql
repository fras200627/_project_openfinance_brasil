insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ((select t.personalid from PERSONAL_DATA t WHERE t.socialname = 'Sebastiana'), '00100008', '0');

insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ((select t.personalid from PERSONAL_DATA t WHERE t.socialname = 'Sebastiana'), '00100010', '1');
  
insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ((select t.personalid from PERSONAL_DATA t WHERE t.socialname = 'Marina'), '00100222', '8');
  
insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ((select t.personalid from PERSONAL_DATA t WHERE t.socialname = 'Isis'), '00105333', '2');
  
insert into ofb.account_personal_data
  (personalid, accountnumber, 
   accountcheckdigit 
)
values
  ((select t.personalid from PERSONAL_DATA t WHERE t.socialname = 'Eduardo'), '00102121', '4');  
/  
COMMIT;
/
