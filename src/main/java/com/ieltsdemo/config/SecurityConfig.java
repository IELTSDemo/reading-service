package com.ieltsdemo.config;

import com.ieltsdemo.security.jwt.EmailAuthorizationFilter;
import com.ieltsdemo.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final EmailAuthorizationFilter emailAuthorizationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, EmailAuthorizationFilter emailAuthorizationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.emailAuthorizationFilter = emailAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Включаем CORS
                .csrf(csrf -> csrf.disable()) // Отключаем CSRF для REST API
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless режим
                .authorizeHttpRequests(auth -> auth
                        // Открытые маршруты
                        .requestMatchers("/auth/google", "/", "/home", "/login", "/api/evaluation/submit","api/users" ).permitAll()

                        // Swagger (оставьте `permitAll()` или замените на `authenticated()` для защиты)
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/swagger-config.js").permitAll()

                        // Защищённые маршруты
                        .requestMatchers("/api/upload/**").authenticated()

                        // Любые другие запросы
                        .anyRequest().permitAll()
                )
                .addFilterAfter(emailAuthorizationFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Разрешённые origin'ы
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Разрешённые методы
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept")); // Указываем необходимые заголовки
        configuration.setAllowCredentials(true); // Разрешаем credentials
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
