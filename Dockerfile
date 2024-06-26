FROM openjdk:18-jdk-alpine3.13

EXPOSE 5050

ADD target/Netology_CloudService-0.0.1-SNAPSHOT.jar cloudService.jar

CMD ["java", "-jar", "cloudService.jar"]