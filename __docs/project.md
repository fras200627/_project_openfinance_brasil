# Project Open Finance Brasil 2025 
#### (compiled at 01.09.2025)
### Description ???
    text
---
### Modules OFB - Orchs (Servers)
1. [x] Consents
2. [x] Customers
3. [x] Accounts
4. [x] Resources

### Modules OFB - Atoms (APIs Backend)
1. [x] Consents
2. [x] Customers
3. [x] Accounts
4. [x] Resources

### Modules Structurals
* Discovery Services
* API Gateway
* Authentication Server
* Participants Clients
---
### Environments
#### Dev (local)
* Windows 11
* Oracle XE 21 for Windows 11 in local instalation
* MySQL 8.0 for Windows 11 in local installation
* RabbitMQ for Windows 11 in local installation
* OFB APIs, Listeners and Batches started in IntelliJ

#### Homolog simulation (docker) 
* Windows Sub-System Linux (Ubuntu 22.04 LTS)
* Docker in WSL
  * controled by docker commands line
* Oracle XE 21 image running in Docker
* MySQL 8.0 image running in Docker
* RabbitMQ 3.XX image running in Docker
* OFB APIs, Listeners and Batches images running in Docker

### Production simulation (microk8s)
* Kubernetes in WSL (plugins MicroK8S)
  * All components: 
* Windows Sub-System Linux (Ubuntu 24.04 LTS)
* Oracle XE 21 image running in Kubernetes
* MySQL 8.0 image running in Kubernetes
* RabbitMQ 3.XX image running in Kubernetes
* OFB APIs, Listeners and Batches images running in Images
---
### Projects
1. [x] Projects - Libs
    * sboot-lib-ofb-autoconfigure-amqp
    * sboot-lib-ofb-autoconfigure-handlers
    * sboot-lib-ofb-autoconfigure-interceptors
    * sboot-lib-ofb-autoconfigure-security
    * sboot-lib-ofb-common-tools
2. [x] Projects - Atoms
    * sboot-atom-ofb-consents
    * sboot-atom-ofb-resources
    * sboot-atom-ofb-customers
    * sboot-atom-ofb-accounts
    * sboot-atom-ofb-participants-business
    * sboot-atom-ofb-discovery-services
    * sbbot-atom-ofb-api-gateway
    * sbbot-atom-ofb-authentication-server
    * sboot-atom-ofb-authorization-server
3. [x] Projects - Orchs
    * sboot-camel-orch-ofb-consents
    * sboot-camel-orch-ofb-resources
    * sboot-camel-orch-ofb-customers
    * sboot-camel-orch-ofb-accounts
4. [x] Projects - Listeners
    * sboot-listener-ofb-audit-services
    * sboot-listener-ofb-consents-authorization-services
    * sboot-listener-ofb-consents-cancellation-services
5. [x] Projects - Batches
   * sbatch-ofb-consents-approval-control
   * sbatch-ofb-consents-expiration-control
---
### Databases
* Oracle XE core version 21
* MySQL CE core version 8.0
---
### Integration
* RabbitMQ 3
---
### Development Tools
* IntelliJ
* OpenAPI (3.0.3)
* Swagger and Swagger Editor
* Portainer
* PL/SQL Development
* MySQL Workbench
* RabbitMQ Service Tools

### Project Management
* Git Hub
* Docker Hub
* Maven
* OpenAPI

### Frameworks and Plugins
* Java JDK-17
* org.OpenAPITools
* org.SpringDoc UI tools
* Springboot (2.7.11)
* Spring AMQP
* Spring Apache Camel
* SpringBatch
* SpringCloud (with Eureka NetFlix)
* SpringCloud (with Spring Gateway)
* SpringSecurity (with JWT and OAuth2)
* Resilience4J
* Authentication Server
* Authorization Server
* MapStruct
* JUnit
* Lombok