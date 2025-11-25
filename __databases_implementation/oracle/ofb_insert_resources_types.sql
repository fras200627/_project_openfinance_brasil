insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('RESOURCES' , 'AVAILABLE',
       'Resources', 'Tipo principal e obrigatório para todos os outros types', 'OFB_ADMIN');
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('CUSTOMER' , 'AVAILABLE',
       'Customers', 'Informações de dados cadastrais', 'OFB_ADMIN');       
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('ACCOUNT' , 'AVAILABLE',
       'Contas', 'Conta de depósito à vista, poupança ou pagamento pré-paga', 'OFB_ADMIN');
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('CREDIT_CARD_ACCOUNT' ,  'UNAVAILABLE',
       'Cartões', 'Conta de pagamento pós-paga (Cartão de Crédito)', 'OFB_ADMIN');
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('LOAN' ,  'UNAVAILABLE',
       'Empréstimos', 'Empréstimo', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('FINANCING' ,  'UNAVAILABLE',
       'Financiamentos', 'Financiamento', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('UNARRANGED_ACCOUNT_OVERDRAFT' ,  'UNAVAILABLE',
       'Cheque Especial', 'Cheque Especial', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('INVOICE_FINANCING' ,  'UNAVAILABLE',
       'Financiamentos de Faturas', 'Financiamento de Fatura', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('BANK_FIXED_INCOME' ,  'UNAVAILABLE',
       'Renda-Fixa', 'Renda Fixa Bancária', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('CREDIT_FIXED_INCOME' ,  'UNAVAILABLE',
       'Renda-Fixa Crédito', 'Renda Fixa Crédito', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('VARIABLE_INCOME' ,  'UNAVAILABLE',
       'Renda-Variável', 'Renda Variável', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('TREASURE_TITLE' ,  'UNAVAILABLE',
       'Tesouro Direto', 'Título do Tesouro Direto', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('FUND' ,  'UNAVAILABLE',
       'Fundos', 'Fundo de Investimento', 'OFB_ADMIN'); 
insert into ofb.RESOURCES_TYPES
  (type, status, summary, description,  user_code)
VALUES ('EXCHANGE',  'UNAVAILABLE',
       'Câmbio', 'Câmbio', 'OFB_ADMIN'); 
/
COMMIT;
/

