FROM eclipse-temurin:23
COPY target/computers-0.0.1-SNAPSHOT.jar computers-0.0.1-SNAPSHOT.jar
ENV GATEWAY_URI=gateway-container
ENV GATEWAY_PORT=8080
ENV SHOPS_URI=shops-container
ENV SHOPS_PORT=8082
ENV COMPUTERS_URI=computers-container
ENV COMPUTERS_PORT=8083
ENTRYPOINT ["java","-jar","/computers-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083


# spring.application.name=gateway

# gatewayName=${GATEWAY_URI:localhost}
# gatewayPort=${GATEWAY_PORT:8080}

# shopsName=${SHOPS_URI:localhost}
# shopsPort=${SHOPS_PORT:8082}

# computersName=${COMPUTERS_URI:localhost}
# computersPort=${COMPUTERS_PORT:8083}
