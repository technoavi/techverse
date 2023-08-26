

FROM openjdk:17-jdk-slim
LABEL APPLICATION="merchant-service"
#MAINTAINER technoAvi
WORKDIR /app
COPY ./target/merchant-service-0.0.1-SNAPSHOT.jar merchant-service.1.0.jar
CMD ["java", "-jar","merchant-service.1.0.jar"]