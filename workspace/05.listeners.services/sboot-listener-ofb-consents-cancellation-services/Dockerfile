FROM openjdk:17
ARG JAR_FILE=target/tican-audit-services-api-1.0.0.jar
ADD ${JAR_FILE} tican-audit-services-api.jar
ENTRYPOINT ["java", "-jar", "/tican-audit-services-api.jar"]
#EXPOSE 3000/tcp
