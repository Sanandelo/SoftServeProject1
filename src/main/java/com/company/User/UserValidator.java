package com.company.User;

import java.time.LocalDate;
import java.time.Period;

public class UserValidator {

    private static String phonePattern = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}";
    private static String namePattern = "[A-Z][a-zA-Z]*";
    private static String surnamePattern = "[a-zA-z]+([ '-][a-zA-Z]+)*";

    public boolean isValidName(String name) {
        return (!name.equals("")) && name.matches(namePattern);
    }

    public boolean isValidSurname(String name) {
        return (!name.equals("")) && name.matches(surnamePattern);
    }

    public boolean isValidDateOfBirth(LocalDate date) {
        Period period = Period.ofYears(18);
        LocalDate minLimit = LocalDate.of(1900, 1, 1);
        LocalDate maxLimit = LocalDate.now().minus(period);
        return date.isAfter(minLimit) && date.isBefore(maxLimit);
    }
    public boolean isValidPhoneNumber(String number) {
        return  (!number.equals("")) && number.matches(phonePattern);
    }

}