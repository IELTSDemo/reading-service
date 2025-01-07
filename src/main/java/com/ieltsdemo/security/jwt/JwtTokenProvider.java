package com.ieltsdemo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "N2NjZWExM2M1MTgyMzg2ZTI3ZTgxNzgzMzA5Njg5NTQwNzRjZjk0YjkwMThmNjExZTQyZDZkYjAzNjMzMjA1ZDMwYzZlNzJlNjIzZTYz"; // Надёжный секретный ключ
    private static final long EXPIRATION_TIME = 86400000; // 24 часа

    // Генерация ключа на основе строки
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Сохраняем email пользователя
                .setIssuedAt(new Date()) // Время выдачи токена
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Срок действия токена
                .signWith(key) // Подписываем токен с использованием SecretKey
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key) // Устанавливаем ключ
                    .build() // Строим JwtParser
                    .parseClaimsJws(token); // Проверяем токен
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // Устанавливаем ключ
                .build() // Строим JwtParser
                .parseClaimsJws(token) // Парсим токен
                .getBody(); // Получаем полезную нагрузку
        return claims.getSubject(); // Возвращаем email пользователя
    }
}
