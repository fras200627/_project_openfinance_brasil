FROM openjdk:17
ARG JAR_FILE=target/tican-oauth2-users-domain-api-1.0.0.jar
ADD ${JAR_FILE} tican-oauth2-users-domain-api.jar
ENTRYPOINT ["java", "-jar", "/tican-oauth2-users-domain-api.jar"]
#EXPOSE 3000/tcp

