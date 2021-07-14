package com.codecool.pokedex.security;

import com.codecool.pokedex.security.filter.JwtAuthenticationFilter;
import com.codecool.pokedex.config.JwtConfiguration;
import com.codecool.pokedex.security.filter.JwtTokenVerifierFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final SecretKey secretKey;
    private final AuthenticationProvider authenticationProvider;
    private final JwtConfiguration jwtConfiguration;
    private final JwtTokenVerifierFilter tokenVerifierFilter;

    @Autowired
    public WebSecurity(AuthenticationProvider authenticationProvider, SecretKey secretKey, JwtTokenVerifierFilter tokenVerifierFilter, JwtConfiguration jwtConfiguration) throws Exception {
        this.authenticationProvider = authenticationProvider;
        this.tokenVerifierFilter = tokenVerifierFilter;
        this.jwtConfiguration = jwtConfiguration;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), secretKey, jwtConfiguration))
                .authorizeRequests()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(tokenVerifierFilter, JwtAuthenticationFilter.class)
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
