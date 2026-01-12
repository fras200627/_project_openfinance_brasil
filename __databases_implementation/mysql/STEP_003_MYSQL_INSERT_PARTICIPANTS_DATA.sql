/*
-- Query: SELECT * FROM user.oauth2_registered_client
-- Date: 2025-11-25 15:33
-- All registers use a client_secret = 123456
*/
-- CREATE SCHEMA user;
-- CREATE DATABASE  IF NOT EXISTS `user` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `user`;

INSERT INTO user.oauth2_registered_client 
(`id`,`client_id`,`client_id_issued_at`,`client_secret`,`client_secret_expires_at`,`client_name`,`client_authentication_methods`,`authorization_grant_types`,`redirect_uris`,`scopes`,`client_settings`,`token_settings`,`status`,`document`,`document_type`) 
VALUES 
('0','ofb-admin-master','2025-05-26 12:55:06','$2a$10$.fK/WxRdoMtSHI8qTRYJHu0zKXx.QcV2MKmvMqQf95GvkauN4fLEu',
'2025-12-31 23:59:59',
'Client OFB Admin Master',
'client_secret_basic','client_credentials','','system.admin',
'{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",315360000.000000000]}',
'ACTIVE','85745999000150','CNPJ');

INSERT INTO user.oauth2_registered_client 
(`id`,`client_id`,`client_id_issued_at`,`client_secret`,`client_secret_expires_at`,`client_name`,`client_authentication_methods`,`authorization_grant_types`,`redirect_uris`,`scopes`,`client_settings`,`token_settings`,`status`,`document`,`document_type`) 
VALUES 
('1','ofb-santander','2025-05-26 12:34:20','$2a$10$xoBSocra.VMPA1TYTCS3QevMWaolfkptPpSizOcYvllivcDBcRwj2','2025-12-31 23:59:59',
'Client OFB Banco Santander','client_secret_basic','client_credentials','','client.ofb.read,client.ofb.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"participanteCNPJ\":\"001002003000100\",\"settings.client.require-authorization-consent\":false,\"participante\":\"Banco Santander S/A\",\"settings.client.require-proof-key\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}','ACTIVE','94420534000110','CNPJ');

INSERT INTO user.oauth2_registered_client
(`id`,`client_id`,`client_id_issued_at`,`client_secret`,`client_secret_expires_at`,`client_name`,`client_authentication_methods`,`authorization_grant_types`,`redirect_uris`,`scopes`,`client_settings`,`token_settings`,`status`,`document`,`document_type`) 
VALUES 
('2','ofb-banco-bv','2025-05-26 12:50:18','$2a$10$KAWu5qfVB14rc1pcNIFRDe70QCsntBMw1Q7wbrwqUeIuWEK1a3aKC','2025-12-31 23:59:59',
'Client OFB Banco Votorantim','client_secret_basic','client_credentials','','client.ofb.read,client.ofb.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}','ACTIVE','47986934000108','CNPJ');

INSERT INTO user.oauth2_registered_client
(`id`,`client_id`,`client_id_issued_at`,`client_secret`,`client_secret_expires_at`,`client_name`,`client_authentication_methods`,`authorization_grant_types`,`redirect_uris`,`scopes`,`client_settings`,`token_settings`,`status`,`document`,`document_type`) 
VALUES 
('3','ofb-c6-bank','2025-05-26 13:08:39','$2a$10$P/Eu6cTgQubUtMr6CEwOweaJXocWvFZjCuK8BfCtiSeiiAS4.KR1W','2025-12-31 23:59:59',
'Client OFB C6 Bank','client_secret_basic','client_credentials','','client.ofb.read,client.ofb.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}','ACTIVE','95916515000142','CNPJ');

commit;
