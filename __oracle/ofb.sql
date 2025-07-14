--DELETE from OFB.RESOURCES_PERMISSIONS t
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'RESOURCES'), 0, 'Resources', 0, 'Informações sobre resources', 0, 'RESOURCES_READ', 'false', 'Por recurso/grupo');


insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 1, 'Cadastro PF - Dados Cadastrais', 1, 'CUSTOMERS_PERSONAL_IDENTIFICATIONS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 2, 'Cadastro PF - Informações Complementares', 1, 'CUSTOMERS_PERSONAL_ADITTIONALINFO_READ', 'true', 'Por recurso');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 3, 'Cadastro PJ - Dados Cadastrais', 1, 'CUSTOMERS_BUSINESS_IDENTIFICATIONS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CUSTOMER'), 1, 'Cadastro', 4, 'Cadastro PJ - Informações Complementares', 1, 'CUSTOMERS_BUSINESS_ADITTIONALINFO_READ', 'true', 'Por recurso');


insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 1, 'Contas', 1, 'ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 1, 'Contas-Saldos', 2, 'ACCOUNTS_BALANCES_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 2, 'Contas-Limites', 1, 'ACCOUNTS_OVERDRAFT_LIMITS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'ACCOUNT'), 2, 'Contas', 3, 'Contas-Extratos', 1, 'ACCOUNTS_TRANSACTIONS_READ', 'true', 'Por recurso');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 1, 'Cartões', 1, 'CREDIT_CARDS_ACCOUNTS_READ', 'false', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 1, 'Cartões-Limites', 2, 'CREDIT_CARDS_ACCOUNTS_LIMITS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 2, 'Cartões-Transações', 1, 'CREDIT_CARDS_ACCOUNTS_TRANSACTIONS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 3, 'Cartões-Faturas', 1, 'CREDIT_CARDS_ACCOUNTS_BILLS_READ', 'true', 'Por recurso');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_CARD_ACCOUNT'), 3, 'Cartão de Crédito', 3, 'Cartões-Faturas Lançamentos', 2, 'CREDIT_CARDS_ACCOUNTS_BILLS_TRANSACTIONS_READ', 'true', 'Por recurso');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Empréstimos', 1, 'LOANS_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Empréstimos-Garantias', 2, 'LOANS_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Empréstimos-Parcelas Agendadas', 3, 'LOANS_SCHEDULED_INSTALMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'LOAN'), 4, 'Operações de Crédito', 1, 'Empréstioms-Pagamentos Efetuados', 4, 'LOANS_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Financiamentos', 1, 'FINANCINGS_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Financiamentos-Garantias', 2, 'FINANCINGS_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Financiamentos-Parcelas Agendadas', 3, 'FINANCINGS_SCHEDULED_INSTALMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FINANCING'), 4, 'Operações de Crédito', 2, 'Financiamentos-Pagamentos Efetuados', 4, 'FINANCINGS_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Cheque-Especial', 1, 'UNARRANGED_ACCOUNTS_OVERDRAFT_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Cheque-Especial-Garantias', 2, 'UNARRANGED_ACCOUNTS_OVERDRAFT_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Cheque-Especial- Saldo Negativo', 3, 'UNARRANGED_ACCOUNTS_OVERDRAFT_SCHEDULED_INSTALMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'UNARRANGED_ACCOUNT_OVERDRAFT'), 4, 'Operações de Crédito', 3, 'Cheque-Especial-Pagamentos Programados', 4, 'UNARRANGED_ACCOUNTS_OVERDRAFT_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Financiamentos-Faturas', 1, 'INVOICE_FINANCINGS_READ', 'false', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Financiamentos-Faturas  - Garantias', 2, 'INVOICE_FINANCINGS_WARRANTIES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Financiamentos-Faturas - Agendamentos Programados', 3, 'INVOICE_FINANCINGS_SCHEDULED_INSTALMENTS_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'INVOICE_FINANCING'), 4, 'Operações de Crédito', 4, 'Financiamentos-Faturas - Pagamentos Efetuados', 4, 'INVOICE_FINANCINGS_PAYMENTS_READ', 'true', 'Por agrupamento de produtos');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'BANK_FIXED_INCOME'), 5, 'Investimento', 1, 'Investimentos Renda-Fixa', 1, 'BANK_FIXED_INCOMES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'CREDIT_FIXED_INCOME'), 5, 'Investimento', 2, 'Investimentos Renda-Fixa Crédito', 1, 'CREDIT_FIXED_INCOMES_READ', 'true', 'Por agrupamento de produtos');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'FUND'), 5, 'Investimento', 1, 'Investimentos-Fundos', 1, 'FUNDS_READ', 'true', 'Por agrupamento de produtos');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'VARIABLE_INCOME'), 5, 'Investimento', 2, 'Investimentos Renda-Variável', 1, 'VARIABLE_INCOMES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'TREASURE_TITLE'), 5, 'Investimento', 2, 'Investimentos-Títulos do Tesouro', 1, 'TREASURE_TITLES_READ', 'true', 'Por agrupamento de produtos');

insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 1, 'Operações de Câmbio', 1, 'EXCHANGES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 2, 'Operações de Câmbio - Detalhes da Operação', 1, 'EXCHANGES_READ', 'true', 'Por agrupamento de produtos');
insert into ofb.resources_permissions (resourcetypeid, permissioncategoryid, permissioncategory, permissioneventgroupid, permissioncategorygroup, permissioncategoryorder, permission, ispermissionevent, permissiongrouping)
VALUES((SELECT RESOURCETYPEID FROM OFB.RESOURCES_TYPES WHERE TYPE = 'EXCHANGE'), 6, 'Câmbio', 3, 'Operaçoes de Câmbio - Eventos', 1, 'EXCHANGES_READ', 'true', 'Por agrupamento de produtos');
/
COMMIT;
/