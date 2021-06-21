FROM openjdk:11
MAINTAINER "Hennan Gadelha"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} proposta.jar
ENTRYPOINT ["java","-jar","proposta.jar"]
EXPOSE 8080
