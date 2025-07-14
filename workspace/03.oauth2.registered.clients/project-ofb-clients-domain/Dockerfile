FROM openjdk:17
ARG JAR_FILE=target/ofb-oauth2-registered-clients-domain-api-1.0.1-openapi.jar
ADD ${JAR_FILE} api_ofb.jar
ENTRYPOINT ["java", "-jar", "/api_ofb.jar"]
#EXPOSE 3000/tcp
