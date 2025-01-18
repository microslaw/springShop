package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class ComputerShopGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerShopGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        String host = "localhost:8080";
        return builder
                .routes()
                .route("shops", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/api/shops",
                                "/api/shops/{name}"
                        )
                        .uri("http://localhost:8082")
                )
                .route("computers", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/api/computers",
                                "/api/shops/{name}/computer_list",
                                "/api/shops/{name}/computer_list/{model}"
                        )
                        .uri("http://localhost:8083")
                )
                .build();
    }
}
