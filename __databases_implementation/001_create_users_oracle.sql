/***********************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

-- Drop All users TICAN
--DROP USER TICAN2 CASCADE;
--DROP USER TICAN2_OWNER CASCADE;
--DROP USER TICAN2_USER CASCADE;

-- Create TICAN user
CREATE USER TICAN2
  IDENTIFIED BY tican
  DEFAULT TABLESPACE SYSTEM
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/
GRANT ALL PRIVILEGES TO TICAN2;
/
/***********************************************************************
* Step 2 - Login  Session with TICAN2
*  create user TICAN_OWNER
************************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
/
-- Create TICAN_OWNER  
CREATE USER TICAN2_OWNER
  IDENTIFIED BY tican
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/  
GRANT CONNECT, RESOURCE, DBA TO TICAN2_OWNER;
/
-- Create TICAN_USER 
CREATE USER TICAN2_USER
  IDENTIFIED BY tican
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
/  
GRANT CONNECT TO TICAN2_USER;
/

/***********************************************************************
* Step 2.1 - Grant execute encrypt and decrypt passwords
*  create user SYS at SYSDBA 
************************************************************************/
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO TICAN2;
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO TICAN2_OWNER;
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO TICAN2_USER;
/
