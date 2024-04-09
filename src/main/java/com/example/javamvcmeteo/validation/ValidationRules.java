package com.example.javamvcmeteo.validation;

import java.util.regex.Pattern;

public class ValidationRules {
    private final Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");

    public  boolean isValid (String stringPatikirnti) {
        return pattern.matcher(stringPatikirnti).matches();
    }
}
