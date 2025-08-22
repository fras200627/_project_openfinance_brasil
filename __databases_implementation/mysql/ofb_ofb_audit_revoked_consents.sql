CREATE DATABASE  IF NOT EXISTS `ofb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ofb`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ofb
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
-- Table structure for table `ofb_audit_revoked_consents`
--

DROP TABLE IF EXISTS `ofb_audit_revoked_consents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ofb_audit_revoked_consents` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATEAT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `XTICKETID` varchar(20) NOT NULL,
  `XFAPIINTERACTIONID` varchar(100) NOT NULL,
  `CONSENTID` varchar(256) NOT NULL,
  `CONSENTREQUESTDATE` timestamp NOT NULL,
  `CONSENTREVOKEDDATE` timestamp NOT NULL,
  `REASON` varchar(500) NOT NULL,
  `PAYLOAD` varchar(6000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=971544199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofb_audit_revoked_consents`
--

LOCK TABLES `ofb_audit_revoked_consents` WRITE;
/*!40000 ALTER TABLE `ofb_audit_revoked_consents` DISABLE KEYS */;
INSERT INTO `ofb_audit_revoked_consents` VALUES (767029068,'2025-07-30 20:39:01','256332622','78fc4e5-37ca-4da3-adf2-9b082bf92280','urn:bancotcn:d3996431-b403-4f6b-b305-fc68266ebc44','2025-07-30 20:39:01','2025-07-30 20:39:01','CUSTOMER_MANUALLY_REVOKED','{\"consentId\":\"urn:bancotcn:d3996431-b403-4f6b-b305-fc68266ebc44\",\"creationDatetime\":\"Jul 29, 2025, 2:26:03 PM\",\"consentStatusId\":84,\"status\":\"REJECTED\",\"accessTokenAuthorised\":\"\",\"statusUpdateDatetime\":\"Jul 29, 2025, 3:11:33 PM\",\"expirationDatetime\":\"Oct 29, 2025, 11:59:59 PM\",\"expirationDateInfo\":\"TRIMESTRAL\",\"personalId\":\"custpf-538-343BC2B5A48B984AB69F16FECCC1A\",\"awaitingAuthBy\":\"73991016982\",\"awaitingAuthStart\":\"Jul 29, 2025, 2:26:17 PM\",\"awaitingAuthEnd\":\"Jul 29, 2025, 2:26:42 PM\",\"awaitingAuthAdditionalInfo\":\"awaiting finished\",\"authorisedStart\":\"Jul 29, 2025, 2:26:42 PM\",\"authorisedEnd\":\"Jul 29, 2025, 2:26:42 PM\",\"authorisedAdditionalInfo\":\"authorised finished\",\"rejectedBy\":\"USER\",\"rejectedCode\":\"CUSTOMER_MANUALLY_REVOKED\",\"rejectedReason\":\"CUSTOMER_MANUALLY_REVOKED\",\"rejectedAdditionalInfo\":\"x-ticket-id [256332622] x-fapi-interaction-id [78fc4e5-37ca-4da3-adf2-9b082bf92280]\",\"rejectedStartDatetime\":\"Jul 29, 2025, 3:11:33 PM\",\"rejectedEndDatetime\":\"Jul 29, 2025, 3:11:33 PM\",\"createAt\":\"Jul 29, 2025, 2:58:05 PM\",\"modifyAt\":\"Jul 29, 2025, 3:11:33 PM\",\"userCode\":\"ConsentsServiceAPI\"}');
/*!40000 ALTER TABLE `ofb_audit_revoked_consents` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-22 10:12:03
