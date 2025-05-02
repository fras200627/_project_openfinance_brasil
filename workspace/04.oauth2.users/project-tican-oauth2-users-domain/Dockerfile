FROM openjdk:17
ARG JAR_FILE=target/tican-oauth2-users-domain-api-1.0.0-openapi.jar
ADD ${JAR_FILE} api_tican.jar
ENTRYPOINT ["java", "-jar", "/api_tican.jar"]
#EXPOSE 3000/tcp


