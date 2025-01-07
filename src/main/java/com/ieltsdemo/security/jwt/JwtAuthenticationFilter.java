package com.ieltsdemo.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 1. Проверяем JWT-токен в заголовке Authorization
            String token = getTokenFromRequest(request);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                setAuthentication(token);
            }

            // 2. Проверяем API-ключ (для приложений)
            String apiKey = request.getHeader("X-API-KEY");
            if (apiKey != null && isValidApiKey(apiKey)) {
                setApplicationAuthentication(apiKey);
            }

        } catch (Exception ex) {
            // Логируем ошибки для отладки
            logger.error("Ошибка авторизации: " + ex.getMessage(), ex);
        }

        // Передаём запрос дальше в цепочке фильтров
        filterChain.doFilter(request, response);
    }

    /**
     * Извлекает токен из заголовка Authorization
     *
     * @param request HTTP-запрос
     * @return Токен или null, если заголовок отсутствует
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    /**
     * Проверка API-ключа
     *
     * @param apiKey Ключ, переданный в заголовке X-API-KEY
     * @return true, если ключ валиден
     */
    private boolean isValidApiKey(String apiKey) {
        // Пример проверки API-ключа (замените на логику проверки в вашей БД или конфигурации)
        return "12345".equals(apiKey);
    }

    /**
     * Устанавливает аутентификацию на основе JWT токена
     *
     * @param token JWT токен
     */
    private void setAuthentication(String token) {
        String email = jwtTokenProvider.getEmailFromToken(token);

        // Устанавливаем пользователя в контекст безопасности
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(email, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Устанавливает аутентификацию для приложений (на основе API-ключа)
     *
     * @param apiKey API-ключ
     */
    private void setApplicationAuthentication(String apiKey) {
        // В данном примере API-ключ соответствует "application" (вы можете дополнить данными из БД)
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("application", null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
