package com.codecool.pokedex.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    public JwtSecretKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public SecretKey getSecretKey() {
        return new SecretKeySpec(jwtConfig.getSecretKey().getBytes(), "HmacSHA256");
    }
}
