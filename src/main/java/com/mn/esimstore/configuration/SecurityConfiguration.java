package com.mn.esimstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;

import com.mn.esimstore.security.jwt.JwtTokenGenerator;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public JwtTokenGenerator jwtTokenGenerate() {
        return new JwtTokenGenerator();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
