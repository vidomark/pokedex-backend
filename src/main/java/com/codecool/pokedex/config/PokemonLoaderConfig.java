package com.codecool.pokedex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("pokemon")
public class PokemonLoaderConfig {

    private Boolean load;

    private Integer number;

    public Boolean getLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
