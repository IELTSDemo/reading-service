package com.ieltsdemo.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllowedEmailsService {
    // Список разрешённых email
    private static final List<String> ALLOWED_EMAILS = List.of(
            "rualferz@gmail.com"
    );

    public boolean isEmailAllowed(String email) {
        return ALLOWED_EMAILS.contains(email);
    }
}
