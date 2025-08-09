package com.fiap.java.restaurante.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class ConfiguracoesSeguranca {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService usuarioService) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // facilita uso no Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**").authenticated()
                        .requestMatchers("/usuario").permitAll()// exige login para /admin
                        .anyRequest().permitAll() // permite todos os outros
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(usuarioService); // usa autenticação HTTP Basic

        return http.build();
    }
}
