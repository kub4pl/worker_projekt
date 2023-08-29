package com.example.workerapplication.validators;

public class BankAccountValidator {

    public static boolean isValidBankAccountNumber(String input)
    {
        input = input.replace(" ", "");
        if (input.length() != 26 && input.length() != 32)
        {
            return false;
        }

        final int countryCode = 2521;
        String checkSum = input.substring(0, 2);
        String accountNumber = input.substring(2);
        String reversedDigits = accountNumber + countryCode + checkSum;
        return modString(reversedDigits, 97) == 1;
    }

    private static int modString(String x, int y)
    {
        if (x == null || x.isEmpty())
        {
            return 0;
        }
        String firstDigit = x.substring(0, x.length() - 1); // first digits
        int lastDigit = Integer.parseInt(x.substring(x.length() - 1)); // last digit
        return (modString(firstDigit, y)*10 + lastDigit)%y;
    }
}
