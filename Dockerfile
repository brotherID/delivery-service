FROM openjdk:17-alpine
COPY target/deliveryManagement-0.0.1-SNAPSHOT.jar deliveryManagement.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "deliveryManagement.jar"]
