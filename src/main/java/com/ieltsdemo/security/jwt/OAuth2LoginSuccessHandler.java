package com.ieltsdemo.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    public OAuth2LoginSuccessHandler(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Извлекаем пользователя OAuth2
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = (String) oauthUser.getAttributes().get("email");

        // Генерируем JWT-токен на основе email
        String token = jwtTokenProvider.createToken(email);

        // Получаем redirect URI из сессии, если он был сохранён ранее
        String redirectUri = (String) request.getSession().getAttribute("redirect_uri");
        if (redirectUri == null) {
            redirectUri = "/";
        }

        // Добавляем токен в качестве параметра запроса к URI перенаправления
        String redirectWithToken = redirectUri + "?token=" + token;
        response.sendRedirect(redirectWithToken);
    }
}
