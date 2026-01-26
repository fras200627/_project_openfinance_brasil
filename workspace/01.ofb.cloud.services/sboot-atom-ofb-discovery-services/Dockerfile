FROM amazoncorretto:17.0.7-alpine
ARG JAR_FILE=target/sboot-atom-ofb-discovery-services-1.0.0.jar
ADD ${JAR_FILE} api_ofb.jar
ENTRYPOINT ["java", "-jar", "/api_ofb.jar"]
#EXPOSE 30000/tcp
