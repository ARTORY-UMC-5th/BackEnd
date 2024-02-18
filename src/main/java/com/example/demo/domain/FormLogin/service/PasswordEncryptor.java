package com.example.demo.domain.FormLogin.service;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Component;


@Component
public class PasswordEncryptor {
    private static final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public static String encryptPassword(String password) {
        return passwordEncryptor.encryptPassword(password);
    }

    public static boolean checkPassword(String plainPassword, String encryptedPassword) {
        return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
