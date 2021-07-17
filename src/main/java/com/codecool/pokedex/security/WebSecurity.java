package com.codecool.pokedex.security;

import com.codecool.pokedex.security.filter.LoginAuthenticationFilter;
import com.codecool.pokedex.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter tokenVerifierFilter;
    private final LoginAuthenticationFilter loginAuthenticationFilter;

    @Autowired
    public WebSecurity(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter tokenVerifierFilter, LoginAuthenticationFilter loginAuthenticationFilter) throws Exception {
        this.authenticationProvider = authenticationProvider;
        this.tokenVerifierFilter = tokenVerifierFilter;
        this.loginAuthenticationFilter = loginAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(loginAuthenticationFilter)
                .authorizeRequests()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(tokenVerifierFilter, LoginAuthenticationFilter.class)
                .formLogin(); // TODO: implement in header
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
