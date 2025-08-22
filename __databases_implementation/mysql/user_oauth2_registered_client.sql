CREATE DATABASE  IF NOT EXISTS `user` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `user`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: user
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `oauth2_registered_client`
--

DROP TABLE IF EXISTS `oauth2_registered_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth2_registered_client` (
  `id` varchar(100) NOT NULL,
  `client_id` varchar(100) NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) NOT NULL,
  `client_authentication_methods` varchar(1000) NOT NULL,
  `authorization_grant_types` varchar(1000) NOT NULL,
  `redirect_uris` varchar(1000) DEFAULT NULL,
  `scopes` varchar(1000) NOT NULL,
  `client_settings` varchar(2000) NOT NULL,
  `token_settings` varchar(2000) NOT NULL,
  `status` varchar(30) DEFAULT 'LOCKED',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth2_registered_client`
--

LOCK TABLES `oauth2_registered_client` WRITE;
/*!40000 ALTER TABLE `oauth2_registered_client` DISABLE KEYS */;
INSERT INTO `oauth2_registered_client` VALUES ('0','ofb-admin-master','2025-05-26 15:55:06','$2a$10$.fK/WxRdoMtSHI8qTRYJHu0zKXx.QcV2MKmvMqQf95GvkauN4fLEu','2026-01-01 02:59:59','Client OFB Admin Master','client_secret_basic','client_credentials','','system.admin','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",315360000.000000000]}','ACTIVE'),('1','ofb-santander','2025-05-26 15:34:20','$2a$10$xoBSocra.VMPA1TYTCS3QevMWaolfkptPpSizOcYvllivcDBcRwj2','2026-01-01 02:59:59','Client OFB Banco Santander','client_secret_basic','client_credentials','','client.ofb.read,client.ofb.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"participanteCNPJ\":\"001002003000100\",\"settings.client.require-authorization-consent\":false,\"participante\":\"Banco Santander S/A\",\"settings.client.require-proof-key\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}','ACTIVE'),('2','ofb-banco-bv','2025-05-26 15:50:18','$2a$10$KAWu5qfVB14rc1pcNIFRDe70QCsntBMw1Q7wbrwqUeIuWEK1a3aKC','2026-01-01 02:59:59','Client OFB Banco Votorantim','client_secret_basic','client_credentials','','client.ofb.read,client.ofb.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}','LOCKED'),('3','ofb-c6-bank','2025-05-26 16:08:39','$2a$10$P/Eu6cTgQubUtMr6CEwOweaJXocWvFZjCuK8BfCtiSeiiAS4.KR1W','2026-01-01 02:59:59','Client OFB C6 Bank','client_secret_basic','client_credentials','','client.ofb.read,client.ofb.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}','ACTIVE'),('99999','tican-admin-master','2025-06-04 22:35:46','$2a$10$f508myMWjr8c3VEoHMQXFu3HDOJSPa.NAm4Sku97HozQ9Go/qJxx.','2035-06-02 22:35:46','Client Tican Admin Master','client_secret_basic','client_credentials','','client.root,client.read,client.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.core.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}','ACTIVE'),('a24e4dd5-8bd1-4d1c-8d98-06a90400d41c','tican-client-2b','2025-06-09 17:02:55','$2a$10$XCTBVeSpIsodJ.1g6.9T5OVo7Qsl02rbDjQowteYdACWFMXjQpHaG','2025-12-06 17:02:55','Tican Client 2b','client_secret_basic','client_credentials','nopnopnopnop3,nopnopnopnop4','client.root','AbstractSettings {settings={settings.client.require-proof-key=false, settings.client.require-authorization-consent=false}}','AbstractSettings {settings={settings.token.reuse-refresh-tokens=true, settings.token.id-token-signature-algorithm=RS256, settings.token.access-token-time-to-live=PT30M, settings.token.access-token-format=org.springframework.security.oauth2.core.OAuth2TokenFormat@face4f32, settings.token.refresh-token-time-to-live=PT1H}}','LOCKED'),('af4beb52-d96b-491d-9bc2-af15f0cb76f4','tican-client-15042025','2025-06-09 17:02:44','$2a$10$F2Wdsr5kRErYs.Sr1admVetv7ZtEdA1m.44xNHE4hhHilAb3PLIDW','2025-12-06 17:02:44','Tican Client 15042025','client_secret_basic','client_credentials','nopnopnopnop3,nopnopnopnop4','client.read,client.write,client.root','AbstractSettings {settings={settings.client.require-proof-key=false, settings.client.require-authorization-consent=false}}','AbstractSettings {settings={settings.token.reuse-refresh-tokens=true, settings.token.id-token-signature-algorithm=RS256, settings.token.access-token-time-to-live=PT30M, settings.token.access-token-format=org.springframework.security.oauth2.core.OAuth2TokenFormat@face4f32, settings.token.refresh-token-time-to-live=PT1H}}','LOCKED');
/*!40000 ALTER TABLE `oauth2_registered_client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-22 10:12:07
