package com.ieltsdemo.config;

import com.ieltsdemo.security.jwt.JwtAuthenticationFilter;
import com.ieltsdemo.security.jwt.RedirectUriFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RedirectUriFilter redirectUriFilter;

    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          RedirectUriFilter redirectUriFilter) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.redirectUriFilter = redirectUriFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключение CSRF для API-путей
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**")
                )
                // Настройка маршрутов и доступа
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/login", "/oauth2/authorization/google", "/api/evaluation/submit").permitAll() // Открытые маршруты
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**","/swagger-ui/swagger-config.js").authenticated() // Swagger защищён
                        .requestMatchers("/api/upload/**").authenticated() // API защищено
                        .anyRequest().permitAll() // Остальные маршруты доступны
                )
                // Добавляем JWT фильтр перед UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Добавляем RedirectUriFilter перед UsernamePasswordAuthenticationFilter
                .addFilterBefore(redirectUriFilter, UsernamePasswordAuthenticationFilter.class)
                // Настройка OAuth2 Login
                .oauth2Login()
                .successHandler(customAuthenticationSuccessHandler) // Кастомный обработчик для Google OAuth2
                .and()
                // Настройка Logout
                .logout()
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        return http.build();
    }
}
