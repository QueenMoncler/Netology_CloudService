FROM khipu/openjdk17-alpine

EXPOSE 5050

ADD target/Netology_CloudService-0.0.1-SNAPSHOT.jar cloudService.jar

CMD ["java", "-jar", "cloudService.jar"]