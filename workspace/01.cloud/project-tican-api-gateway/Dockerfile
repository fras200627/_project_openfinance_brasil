FROM openjdk:17
ARG JAR_FILE=target/tican-api-gateway-1.0.0-openapi.jar
ADD ${JAR_FILE} api_tican.jar
ENTRYPOINT ["java", "-jar", "/api_tican.jar"]
#EXPOSE 3000/tcp