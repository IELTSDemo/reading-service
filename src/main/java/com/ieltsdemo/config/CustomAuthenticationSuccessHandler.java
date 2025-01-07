package com.ieltsdemo.config;

import com.ieltsdemo.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    public CustomAuthenticationSuccessHandler(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    private static final List<String> ALLOWED_EMAILS = List.of(
            "rualferz@gmail.com"
    );
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        String email = user.getAttribute("email");
        if (!ALLOWED_EMAILS.contains(email)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Unauthorized email");
            return;
        }
        // Генерируем JWT токен
        String token = jwtTokenProvider.createToken(email);

        // Получаем URL, откуда был начат запрос (параметр state)
        String redirectUri = (String) request.getSession().getAttribute("redirect_uri");

        // Удаляем redirect_uri из сессии
        request.getSession().removeAttribute("redirect_uri");

        // Если redirect_uri отсутствует, перенаправляем на Swagger
        if (redirectUri == null) {
            redirectUri = "/reading-service/swagger-ui/index.html";
        }

        // Перенаправляем пользователя обратно на изначальный URL с токеном
        response.sendRedirect(redirectUri + "?token=" + token);
    }
}
