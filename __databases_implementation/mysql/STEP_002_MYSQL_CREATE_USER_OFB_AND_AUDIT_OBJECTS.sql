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

CREATE TABLE `ofb_audit` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'Id gerado a partir da entidade java.',
  `CREATEAT` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro.',
  `XTICKETID` varchar(20) DEFAULT 'not informed' COMMENT 'Nr único de ticket gerado a partir do código java.',
  `XFAPIINTERACTIONID` varchar(100) DEFAULT 'not informed' COMMENT 'Interaction Id único gerado a partir do código java.',
  `REQUESTTIME` varchar(200) DEFAULT 'not informed' COMMENT 'Data/Hora do request http.',
  `REQUESTURI` varchar(400) DEFAULT 'not informed' COMMENT 'URI do request http',
  `REQUESTMETHOD` varchar(100) DEFAULT 'not informed' COMMENT 'Método http executado.',
  `REQUESTUSERNAME` varchar(100) DEFAULT 'not informed' COMMENT 'User name que executou o request',
  `PAYLOAD` varchar(6000) DEFAULT 'not informed' COMMENT 'Payload JSON executado no request. ',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=999912036 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 
COMMENT='Registros de auditoria de qualquer requisição http executada no OFB.';

CREATE TABLE `ofb_audit_authorization_consents` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATEAT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `XTICKETID` varchar(20) NOT NULL,
  `XFAPIINTERACTIONID` varchar(100) NOT NULL,
  `CONSENTID` varchar(256) NOT NULL COMMENT 'Id do consentimento no formato UUID.',
  `CONSENTREQUESTDATE` timestamp NOT NULL,
  `CONSENTAPPROVEDDATE` timestamp NOT NULL COMMENT 'Data/Hora de aprovação (autorização) do consentimento.',
  `PAYLOAD` varchar(6000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=970558354 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 
COMMENT='Registro de auditoria de todas execuções de autorizações de consentimentos.';

CREATE TABLE `ofb_audit_cancellation_consents` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATEAT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `XTICKETID` varchar(20) NOT NULL,
  `XFAPIINTERACTIONID` varchar(100) NOT NULL,
  `CONSENTID` varchar(256) NOT NULL,
  `CONSENTREQUESTDATE` timestamp NOT NULL,
  `CONSENTCANCELDATE` timestamp NOT NULL COMMENT 'Data/Hora da execução do concelamento.',
  `REASON` varchar(500) NOT NULL COMMENT 'Motivo do concelamento.',
  `PAYLOAD` varchar(6000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=971544199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 
COMMENT='Registro de auditoria de qualquer execução de cancelamento de consentimento.';

CREATE TABLE `ofb_audit_extends_consents` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATEAT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `XTICKETID` varchar(20) NOT NULL,
  `XFAPIINTERACTIONID` varchar(100) NOT NULL,
  `CONSENTID` varchar(256) NOT NULL,
  `CONSENTREQUESTDATE` timestamp NOT NULL,
  `CONSENTEXTENDSDATE` timestamp NOT NULL COMMENT 'Data/Hora de registro da prorrogação do consentimento.',
  `PAYLOAD` varchar(6000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=958532028 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 
COMMENT='Registro de auditoria de qualquer solicação de prorrogação de validade da autorização dos consentimentos.';

CREATE TABLE `ofb_audit_revoked_consents` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATEAT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `XTICKETID` varchar(20) NOT NULL,
  `XFAPIINTERACTIONID` varchar(100) NOT NULL,
  `CONSENTID` varchar(256) NOT NULL,
  `CONSENTREQUESTDATE` timestamp NOT NULL,
  `CONSENTREVOKEDDATE` timestamp NOT NULL COMMENT 'Data/Hora do registro de cancelemento do consentimento.',
  `REASON` varchar(500) NOT NULL COMMENT 'Inofrmação do motivo de cancelamento.',
  `PAYLOAD` varchar(6000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=971544199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 
COMMENT='Registro de auditoria de qualquer solicitação de cancelamento dos consentimentos';

