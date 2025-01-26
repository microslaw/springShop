FROM eclipse-temurin:23
COPY target/shops-0.0.1-SNAPSHOT.jar shops-0.0.1-SNAPSHOT.jar

ENV GATEWAY_URI_URI=shops-container
ENV GATEWAY_URI_PORT=8080
ENV SHOPS_URI=shops-container
ENV SHOPS_PORT=8082
ENV COMPUTERS_URI=computers-container
ENV COMPUTERS_PORT=8083

ENTRYPOINT ["java","-jar","/shops-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082
