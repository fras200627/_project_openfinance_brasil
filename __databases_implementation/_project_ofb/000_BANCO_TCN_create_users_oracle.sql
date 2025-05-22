/***********************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

--Drop All users BANCO_TCN
--DROP USER BANCO_TCN CASCADE;
--DROP USER BANCO_TCN_OWNER CASCADE;
--DROP USER BANCO_TCN_USER CASCADE;

-- Create schema BANCO_TCN
CREATE USER BANCO_TCN
  IDENTIFIED BY bancotcn
  DEFAULT TABLESPACE SYSTEM
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/
GRANT ALL PRIVILEGES TO BANCO_TCN;
/
/***********************************************************************
* Step 2 - Login  Session with BANCO_TCN
*  create user BANCO_TCN_OWNER
************************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
/
-- Create BANCO_TCN_OWNER  
CREATE USER BANCO_TCN_OWNER
  IDENTIFIED BY bancotcn
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/  
GRANT CONNECT, RESOURCE, DBA TO BANCO_TCN_OWNER;
/
-- Create TICAN_USER 
CREATE USER BANCO_TCN_USER
  IDENTIFIED BY bancotcn
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/  
GRANT CONNECT TO BANCO_TCN_USER;
/

/***********************************************************************
* Step 2.1 - Grant execute encrypt and decrypt passwords
*  create user SYS at SYSDBA 
************************************************************************/
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO BANCO_TCN;
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO BANCO_TCN_OWNER;
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO BANCO_TCN_USER;
/
