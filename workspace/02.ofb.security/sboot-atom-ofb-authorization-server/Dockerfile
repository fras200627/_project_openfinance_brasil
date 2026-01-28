FROM amazoncorretto:17.0.7-alpine
ARG JAR_FILE=target/sboot-atom-ofb-authorization-server-1.0.0.jar
ADD ${JAR_FILE} api_ofb.jar
ENTRYPOINT ["java", "-jar", "/api_ofb.jar"]
#EXPOSE 3000/tcp
