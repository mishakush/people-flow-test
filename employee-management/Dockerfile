FROM openjdk:16-jdk-alpine
MAINTAINER mikhail
COPY target/*.jar employee-management.jar
ENTRYPOINT ["java","-jar","-Dspring.config.name=employee-management,employee-management-access,bootstrap", "/employee-management.jar"]