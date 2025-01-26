package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ComputerShopGatewayApplication {

    @Value("${gatewayName}")
    private String hostName;
    @Value("${gatewayPort}")
    private String hostPort;

    @Value("${shopsName}")
    private String shopsName;
    @Value("${shopsPort}")
    private String shopsPort;

    @Value("${computersName}")
    private String computersName;
    @Value("${computersPort}")
    private String computersPort;

    public static void main(String[] args) {
        SpringApplication.run(ComputerShopGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        String httpShopsUri = "http://" + shopsName + ":" + shopsPort;
        String httpComputersUri = "http://" + computersName + ":" + computersPort;
        String host = "localhost" + ":" + hostPort;

        System.out.println("host: " + host);
        System.out.println("shopsUri: " + httpShopsUri);
        System.out.println("computersUri: " + httpComputersUri);
        return builder
                .routes()
                .route("shops", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/api/shops",
                                "/api/shops/{name}"
                        )
                        .uri(httpShopsUri)
                )
                .route("computers", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/api/computers",
                                "/api/shops/{name}/computer_list",
                                "/api/shops/{name}/computer_list/{model}"
                        )
                        .uri(httpComputersUri)
                )
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
