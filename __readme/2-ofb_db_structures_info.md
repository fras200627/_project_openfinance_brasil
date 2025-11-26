### Database Environments 
#### Dev (local)
* Windows 11
* Oracle XE 21 for Windows 11 in local instalation
* MySQL 8.0 for Windows 11 in local installation

#### Homolog simulation (docker) 
* Windows Sub-System Linux (Ubuntu 22.04 LTS)
* Docker in WSL
  * controled by docker commands line
* Oracle XE 21 image running in Docker
* MySQL 8.0 image running in Docker

#### Production simulation (microk8s)
* Windows Sub-System Linux (Ubuntu 24.04 LTS)
* Kubernetes in WSL (plugins MicroK8S)
  * All components: 
* Oracle XE 21 image running in Kubernetes
* MySQL 8.0 image running in Kubernetes

---
### Database Structures
#### Oracle
* OFB Core and SpringBatch objects control
#### MySQL
* OFB Audit Core and SpringSecurity objects control 

---
### Database Data Objects Structures
#### Oracle
* **Connection**
    - Home: OraDB18Home2
    - DLL: C:\_development\_oraclexe21c\product\21c\dbhomeXE\bin\oci.dll
    - OCI: version 12.1  (21.3.0.0.0)
    - Oracle Database 21c Express Edition Release 21.0.0.0.0 
    - Aliases
      - LISTENER_XE, LISTENER_XE_DOCKER and LISTENER_XE_K8S
      - XE, XE_DOCKER and XE_K8S
    - Access  
        - Admin user: sys (pwd: tican)
        - Admin user: system (pwd: tican)
* **Scripts for implementation**
  - STEP_001_ORCL_CREATE_OFB_USERS.sql
  - STEP_002_ORCL_OFB_CREATE_ALL_OBJECTS.sql
  - STEP_003_ORCL_OFB_GRANTS_TO_USERS.sql
  - STEP_004_ORCL_OFB_INPUT_DATA.sql
  - STEP_005_ORCL_OFB_INPUT_DATA.sql

---
#### Oracle Schema: OFB
All descriptions and details about the purpose of tables, views, and sequences,
as well as the function of each field in the tables and views, are in the Oracle objects themselves
in the comment fields.

* **Users**
    - OFB (pwd: ofb)
    - OFB_OWNER (pwd: ofb)
    - OFB_USER (pwd: ofb)
* **Grants users**
    - OFB user: grant all privileges
    - OFB_OWNER: grant DDL and DML privileges
    - OFB_USER: grant DML privileges (is a OFB apps connection user)
    
* **Objects**
    - **TABLES CORE: PARAMETERS/SETTINGS**
        - ofb.consents_status
        - ofb.resources_permissions
        - ofb.resources_status
        - ofb.resources_types
    
    - **TABLES CORE: PERSONAL CLIENTS AND ACCOUNTS**
      - ofb.personal_data
      - ofb.account_personal_data
      - ofb.account_personal_data_statement

    - **TABLES CORE: CONSENTS DATA**
      - ofb.consents_personal_data
      - ofb.consents_personal_data_expiration_control
      - ofb.consents_personal_data_permissions_authorised
      - ofb.consents_personal_data_permissions_requested
      - ofb.consents_personal_data_resources_authorised
      - ofb.consents_personal_data_resourses_authorised_permissions
    
    - **TABLES AUX: Mandatory in Spring Batch Controls**
      - ofb.batch_job_execution
      - ofb.batch_job_execution_context
      - ofb.batch_job_execution_params
      - ofb.batch_job_instance
      - ofb.batch_step_execution
      - ofb.batch_step_execution_context
    
    - **SEQUENCES CORE: used for all pk fields in OFB tables**
      - ofb.sq_ofb_system
    
    - **SEQUENCES AUX: Mandatory in Spring Batch Controls**
      - ofb.batch_job_execution_seq
      - ofb.batch_job_seq
      - ofb.batch_step_execution_seq
    
    - **VIEWS CORE: PARAMETERS/SETTINGS**
      - ofb.vw_consents_status
      - ofb.vw_resources_permissions
      - ofb.vw_resources_status
      - ofb.vw_resources_types
    
    - **VIEWS CORE: PERSONAL CLIENTS AND ACCOUNTS**
      - ofb.vw_personal_data
      - ofb.vw_account_personal_data
      - ofb.vw_account_personal_data_statement
    
    - **VIEWS CORE: CONSENTS DATA**
      - ofb.vw_consents_personal_data
      - ofb.vw_consents_personal_data_expiration_control
      - ofb.vw_consents_personal_data_permissions_authorised
      - ofb.vw_consents_personal_data_permissions_requested
      - ofb.vw_consents_personal_data_resources_authorised
      - ofb.vw_consents_personal_data_resources_authorised_permissions

    - **Trigger Accounts Status Control**
      - ofb.trg_account_personal_data

### MySQL
All descriptions and details about the purpose of tables, views, and sequences,
as well as the function of each field in the tables and views, are in the Oracle objects themselves
in the comment fields.
* **Connection** 
    - Using
    
    - Aliases
    
    - Access  
      - Admin user: root (pwd: root)
      - Spring Security: user (pwd: user)
      - OFB Audit controls: ofb (pwd: ofb)
* **Schemas**
    - user (used in Spring Security)
    - ofb (used in OFB Audit controls)
* **Scripts for implementation**
    - STEP_001_MYSQL_CREATE_user_USER_AND_SECURITY_OBJECTS.sql
    - STEP_002_MYSQL_CREATE_USER_OFB_AND_AUDIT_OBJECTS.sql
    - STEP_003_MYSQL_INSERT_PARTICIPANTS_DATA.sql
---    
* **MySQL Schema: USER (used in Spring Security)**
    - Table Objects
      - user.authorities
      - user.clientdetails
      - user.group_authorities
      - user.group_members
      - user.groups
      - user.hibernate_sequence
      - user.oauth2_authorization
      - user.oauth2_authorization_consent
      - user.oauth2_authorized_client
      - **user.oauth2_registered_client** << participants records is here
      - user.oauth_access_token
      - user.oauth_approvals
      - user.oauth_client_details
      - user.oauth_client_token
      - user.oauth_code
      - user.oauth_refresh_token
      - user.persistent_logins
      - user.spring_session
      - user.spring_session_attributes
      - user.user_entity
      - user.users
    - Sequence Object
      - **user.hibernate_sequence** << Used in participants pk
---
* **MySQL Schema: OFB (used in OFB Audit controls)**
    - Table Objects
      - ofb.ofb_audit
      - ofb.ofb_audit_authorization_consents
      - ofb.ofb_audit_cancellation_consents
      - ofb.ofb_audit_extends_consents
      - ofb.ofb_audit_revoked_consents



