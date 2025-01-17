package com.ieltsdemo.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@Order(2)
public class EmailAuthorizationFilter extends OncePerRequestFilter {

    private static final String ALLOWED_EMAIL = "rualferz@gmail.com";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Применяем фильтр только к нужным путям
        if (new AntPathRequestMatcher("/api/upload/**").matches(request)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Проверка аутентификации
            if (authentication == null || !authentication.isAuthenticated()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            // Извлечение principal и проверка его типа
            Object principal = authentication.getPrincipal();
            if (!(principal instanceof Jwt)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden: Unsupported authentication type");
                return;
            }

            // Приведение к Jwt и извлечение email
            Jwt jwt = (Jwt) principal;
            String userEmail = jwt.getClaimAsString("email");

            // Проверка разрешённого email
            if (!ALLOWED_EMAIL.equals(userEmail)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden: Access is denied");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
