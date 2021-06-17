package com.codecool.pokedex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("test")
@EnableJpaRepositories(basePackages = "com.codecool.pokedex.dao.pokemon")
@PropertySource("classpath:/application-test.properties")
@EnableTransactionManagement
public class TestDatabaseConfig {
}
