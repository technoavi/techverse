FROM openjdk:17-jdk-slim
#MAINTAINER technoAvi
WORKDIR /app
COPY ./target/customer-service-0.0.1-SNAPSHOT.jar customer-service.1.0.jar
CMD ["java", "-jar","customer-service.1.0.jar"]


#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]
