FROM amazoncorretto:17.0.7-alpine
ARG JAR_FILE=target/ofb-authentication-server-1.0.1-openapi.jar
ADD ${JAR_FILE} api_ofb.jar
ENTRYPOINT ["java", "-jar", "/api_ofb.jar"]
#EXPOSE 3000/tcp
