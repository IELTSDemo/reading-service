package com.ieltsdemo.config;

import com.ieltsdemo.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключение CSRF (так как это REST API)
                .csrf(csrf -> csrf.disable())

                // Указываем, что сервер работает в stateless-режиме
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Настройка маршрутов и доступа
                .authorizeHttpRequests(auth -> auth
                        // Открытые маршруты
                        .requestMatchers("/auth/google", "/", "/home", "/login", "/api/evaluation/submit").permitAll()

                        // Swagger доступен для всех (или оставьте защищённым)
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/swagger-config.js").permitAll()

                        // Защищённые API
                        .requestMatchers("/api/**").authenticated()

                        // Любые другие запросы разрешены
                        .anyRequest().permitAll()
                )

                // Добавляем JWT фильтр перед UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
