package com.codecool.pokedex.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@ConfigurationProperties("application.jwt")
@Data
public class JwtConfig {

    private int expirationDays;
    private String secretKey;
    private String tokenPrefix;

    protected String getAuthorizationHeader() {return HttpHeaders.AUTHORIZATION;}
}
