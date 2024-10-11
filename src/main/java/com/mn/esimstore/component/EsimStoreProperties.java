package com.mn.esimstore.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "config")
@Data
public class EsimStoreProperties {
    private String application;
    private String jwtSecret;
}
