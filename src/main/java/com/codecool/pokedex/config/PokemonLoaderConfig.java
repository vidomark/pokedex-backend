package com.codecool.pokedex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("pokemon")
public class PokemonLoaderConfig {

    private boolean load;

    public boolean getLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }
}
