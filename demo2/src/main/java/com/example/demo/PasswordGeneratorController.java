package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Random;

@RestController
public class PasswordGeneratorController {

    // Задаем строковые константы для каждого типа символов
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz"; // Строчные буквы
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Заглавные буквы
    private static final String NUMERIC_CHARACTERS = "0123456789"; // Цифры
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?"; // Специальные символы

    @GetMapping("/generatePassword")
    public String generatePassword(
            @RequestParam(value = "length", defaultValue = "8") int length, // Длина пароля (по умолчанию 8 символов)
            @RequestParam(value = "includeLowercase", defaultValue = "true") boolean includeLowercase, // Включать ли строчные буквы (по умолчанию true)
            @RequestParam(value = "includeUppercase", defaultValue = "true") boolean includeUppercase, // Включать ли заглавные буквы (по умолчанию true)
            @RequestParam(value = "includeNumeric", defaultValue = "true") boolean includeNumeric, // Включать ли цифры (по умолчанию true)
            @RequestParam(value = "includeSpecial", defaultValue = "true") boolean includeSpecial // Включать ли специальные символы (по умолчанию true)
    ) {
        String characters = ""; // Инициализируем пустую строку для набора символов

        // Добавляем символы в набор в зависимости от выбранных опций
        if (includeLowercase) {
            characters += LOWERCASE_CHARACTERS;
        }
        if (includeUppercase) {
            characters += UPPERCASE_CHARACTERS;
        }
        if (includeNumeric) {
            characters += NUMERIC_CHARACTERS;
        }
        if (includeSpecial) {
            characters += SPECIAL_CHARACTERS;
        }

        Random random = new SecureRandom(); // Используем SecureRandom для генерации случайных чисел
        StringBuilder password = new StringBuilder(length); // Создаем StringBuilder для построения пароля

        // Генерируем случайные символы и добавляем их в пароль
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString(); // Возвращаем сгенерированный пароль в виде строки
    }
}