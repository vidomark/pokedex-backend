package com.codecool.pokedex.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@ConfigurationProperties("application.jwt")
@Data
public class JwtConfiguration {

    private Integer expirationDays;
    private String secretKey;
    private String tokenPrefix;

    public String getAuthorizationHeader() {return HttpHeaders.AUTHORIZATION;}
}
