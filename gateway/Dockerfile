FROM eclipse-temurin:23
COPY target/gateway-0.0.1-SNAPSHOT.jar gateway-0.0.1-SNAPSHOT.jar
ENV GATEWAY_URI=gateway-container
ENV GATEWAY_PORT=8080
ENV SHOPS_URI=shops-container
ENV SHOPS_PORT=8082
ENV COMPUTERS_URI=computers-container
ENV COMPUTERS_PORT=8083
ENTRYPOINT ["java","-jar","/gateway-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083
