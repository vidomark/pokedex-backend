package com.codecool.pokedex.config;

import com.codecool.pokedex.config.JwtConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class SecretKeyConfiguration {

    private final JwtConfiguration JwtConfiguration;

    public SecretKeyConfiguration(JwtConfiguration JwtConfiguration) {
        this.JwtConfiguration = JwtConfiguration;
    }

    @Bean
    public SecretKey getSecretKey() {
        return new SecretKeySpec(JwtConfiguration.getSecretKey().getBytes(), "HmacSHA256");
    }
}
