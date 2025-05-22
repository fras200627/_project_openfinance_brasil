/***********************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

--Drop All users ofb
--DROP USER OFB CASCADE;
--DROP USER OFB_OWNER CASCADE;
--DROP USER OFB_USER CASCADE;

-- Create TICAN user
CREATE USER OFB
  IDENTIFIED BY ofb_tican
  DEFAULT TABLESPACE SYSTEM
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/
GRANT ALL PRIVILEGES TO OFB;
/
/***********************************************************************
* Step 2 - Login  Session with OFB
*  create user OFB_OWNER
************************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
/
-- Create OFB_OWNER  
CREATE USER OFB_OWNER
  IDENTIFIED BY ofb_tican
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/  
GRANT CONNECT, RESOURCE, DBA TO OFB_OWNER;
/
-- Create TICAN_USER 
CREATE USER OFB_USER
  IDENTIFIED BY ofb_tican
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/  
GRANT CONNECT TO OFB_USER;
/

/***********************************************************************
* Step 2.1 - Grant execute encrypt and decrypt passwords
*  create user SYS at SYSDBA 
************************************************************************/
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO OFB;
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO OFB_OWNER;
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO OFB_USER;
/
