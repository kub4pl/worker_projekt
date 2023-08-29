package com.example.workerapplication.validators;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PeselValidator {

    public static boolean isValidPesel(String pesel) {

        // Sprawdzenie, czy podany numer PESEL nie jest null-em i ma długość 11 znaków
        // oraz czy składa się tylko z cyfr
        if (pesel == null || pesel.length() != 11 || !pesel.matches("\\d{11}")) {
            return false;
        }

        // Definicja wag dla poszczególnych cyfr numeru PESEL
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;

        // Pętla iterująca przez pierwsze 10 cyfr numeru PESEL
        for (int i = 0; i < 10; i++) {
            // Dodanie iloczynu cyfry numeru PESEL i wagi do sumy
            sum += Character.getNumericValue(pesel.charAt(i)) * weights[i];
        }

        // Pobranie ostatniej cyfry numeru PESEL (cyfry kontrolnej)
        int controlDigit = Character.getNumericValue(pesel.charAt(10));

        // Obliczenie cyfry kontrolnej na podstawie sumy ważonej i porównanie z rzeczywistą cyfrą kontrolną
        int calculatedControlDigit = 10 - (sum % 10);

        // Specjalny przypadek, gdy obliczona cyfra kontrolna wynosi 10
        if (calculatedControlDigit == 10) {
            calculatedControlDigit = 0;
        }

        // Porównanie obliczonej cyfry kontrolnej z rzeczywistą cyfrą kontrolną
        return calculatedControlDigit == controlDigit;
    }
}