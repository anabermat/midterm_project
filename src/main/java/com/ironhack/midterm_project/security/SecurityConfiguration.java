package com.ironhack.midterm_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(); // Vamos a utilizar basic auth
        http.csrf().disable(); // Desactivamos la protección CSRF porque nosotros no vamos a manejar el HTML
        http.authorizeRequests() // Vamos a estacler la protección de cada endpoint individualmente
                .antMatchers(HttpMethod.GET, "/AccountHolderAccounts/**").hasRole("ACCOUNT HOLDER") // solo los account holders
                .antMatchers(HttpMethod.PATCH, "/AccountHolderAccounts/**").hasRole("ACCOUNT HOLDER")
                .antMatchers(HttpMethod.GET, "/AdminAccounts/**").hasRole("ADMIN") // solo los ADMIN
                .antMatchers(HttpMethod.PATCH, "/AdminAccounts/**").hasRole("ADMIN")// solo los ADMIN
                .antMatchers(HttpMethod.POST, "/AdminAccounts/**").hasAnyRole("ADMIN") // Solo ADMIN
                .anyRequest().permitAll(); // El resto de los endpoints son públicos
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
