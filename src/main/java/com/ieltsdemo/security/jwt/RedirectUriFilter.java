package com.ieltsdemo.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RedirectUriFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Сохраняем исходный URL перед авторизацией
        if (request.getRequestURI().startsWith("/oauth2/authorization")) {
            String redirectUri = request.getHeader("Referer");
            if (redirectUri != null) {
                request.getSession().setAttribute("redirect_uri", redirectUri);
            }
        }

        filterChain.doFilter(request, response);
    }
}
