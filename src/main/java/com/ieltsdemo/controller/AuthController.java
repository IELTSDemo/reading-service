package com.ieltsdemo.controller;

import com.ieltsdemo.security.jwt.JwtTokenProvider;
import com.ieltsdemo.service.AllowedEmailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Controller
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AllowedEmailsService allowedEmailsService;

    public AuthController(JwtTokenProvider jwtTokenProvider, AllowedEmailsService allowedEmailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.allowedEmailsService = allowedEmailsService;
    }

    @PostMapping("/auth/google")
    public ResponseEntity<?> verifyGoogleToken(@RequestBody Map<String, String> request) {
        String googleToken = request.get("token");
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + googleToken;

        // Отправляем запрос на Google для проверки токена
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Токен валиден, получаем email
            Map<String, Object> body = response.getBody();
            assert body != null;
            String email = (String) body.get("email");
            if (!allowedEmailsService.isEmailAllowed(email)) {
                return ResponseEntity.status(403).body("Access Denied: Unauthorized email");
            }
            // Генерация собственного JWT
            String jwt = jwtTokenProvider.createToken(email);

            // Возвращаем JWT токен клиенту
            return ResponseEntity.ok(Map.of(
                    "email", email,
                    "token", jwt,
                    "message", "Token is valid"
            ));
        } else {
            return ResponseEntity.status(401).body("Invalid Google token");
        }
    }
}
