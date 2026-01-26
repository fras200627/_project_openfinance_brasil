FROM amazoncorretto:17.0.7-alpine
ARG JAR_FILE=target/ofb-oauth2-registered-clients-business-api-1.0.3-openapi.jar
ADD ${JAR_FILE} api_ofb.jar
ENTRYPOINT ["java", "-jar", "/api_ofb.jar"]
#EXPOSE 3000/tcp
