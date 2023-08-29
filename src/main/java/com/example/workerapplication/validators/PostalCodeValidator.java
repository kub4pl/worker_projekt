package com.example.workerapplication.validators;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PostalCodeValidator {
    private static final String POSTAL_CODE_PATTERN = "^[0-9]{2}-[0-9]{3}$"; // Wzorzec dla kodu pocztowego w formacie XX-XXX

    public static boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(POSTAL_CODE_PATTERN);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
}
