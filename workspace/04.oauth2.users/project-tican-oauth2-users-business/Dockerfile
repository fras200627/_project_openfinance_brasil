FROM openjdk:17
ARG JAR_FILE=target/tican-oauth2-users-business-api-1.0.0.jar
ADD ${JAR_FILE} tican-oauth2-users-business-api.jar
ENTRYPOINT ["java", "-jar", "/tican-oauth2-users-business-api.jar"]
#EXPOSE 3000/tcp
