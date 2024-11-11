package dev.srushti.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean  //annotates a method that returns a obj you want to put in Application context
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
