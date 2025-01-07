package com.ieltsdemo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/profile")
    public String getUserProfile(@AuthenticationPrincipal OAuth2User principal) {
        // Извлечение данных пользователя
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        // Логика обработки данных пользователя
        System.out.println("User email: " + email);
        System.out.println("User name: " + name);

        return "profile";  // Возвращает страницу профиля
    }
}
