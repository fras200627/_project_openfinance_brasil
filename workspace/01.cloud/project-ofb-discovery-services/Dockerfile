FROM openjdk:17
ARG JAR_FILE=target/ofb-discovery-services-1.0.0-openapi.jar
ADD ${JAR_FILE} api_ofb.jar
ENTRYPOINT ["java", "-jar", "/api_ofb.jar"]
#EXPOSE 30000/tcp
