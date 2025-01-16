//package com.ieltsdemo.security.jwt;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@Order(1)
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        try {
//            // Извлекаем токен из заголовка Authorization
//            String token = getTokenFromRequest(request);
//            if (token != null && jwtTokenProvider.validateToken(token)) {
//                // Устанавливаем аутентификацию, если токен валиден
//                setAuthentication(token);
//            }
//        } catch (Exception ex) {
//            // При ошибке возвращаем 401 Unauthorized
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + ex.getMessage() + "\"}");
//            return;
//        }
//
//        // Передаём запрос дальше в цепочке фильтров
//        filterChain.doFilter(request, response);
//    }
//
//    /**
//     * Извлекает токен из заголовка Authorization
//     *
//     * @param request HTTP-запрос
//     * @return Токен или null, если заголовок отсутствует
//     */
//    private String getTokenFromRequest(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7); // Убираем "Bearer "
//        }
//        return null;
//    }
//
//    /**
//     * Устанавливает аутентификацию на основе JWT токена
//     *
//     * @param token JWT токен
//     */
//    private void setAuthentication(String token) {
//        String email = jwtTokenProvider.getEmailFromToken(token);
//
//        // Создаём объект аутентификации и устанавливаем в SecurityContext
//        UsernamePasswordAuthenticationToken authentication =
//                new UsernamePasswordAuthenticationToken(email, null, null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//}
