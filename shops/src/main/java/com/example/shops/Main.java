package com.example.shops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args).close();

    }

    @Value("${computersName}")
    private String computersName;

    @Value("${computersPort}")
    private String computersPort;

    @Bean
    public RestTemplate getComputersRestTemplate() {
        return new RestTemplateBuilder().rootUri("http://" + computersName + ":" + computersPort).build();
    }
}
