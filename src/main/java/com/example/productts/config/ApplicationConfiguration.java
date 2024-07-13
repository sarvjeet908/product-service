package com.example.productts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate createResttemplate(){
       return new RestTemplate();
    }

    //now whereever i need resttemplate i can @Autowire
}
