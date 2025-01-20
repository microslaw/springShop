//package com.example.computers;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://localhost:4200");
//        config.addAllowedHeader("Access-Control-Allow-Origin");
//        config.addAllowedHeader("Access-Control-Allow-Credentials");
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
//        config.setAllowCredentials(true);
//        config.addExposedHeader("Access-Control-Allow-Origin");
//        config.addExposedHeader("Access-Control-Allow-Credentials");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
//}
