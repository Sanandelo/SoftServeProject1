package com.company.Car;

import java.time.LocalDate;

public class CarValidator {
    private static String modelPattern = "\\b((?=[A-Za-z/ -]{0,19}\\d)[A-Za-z0-9/ -]{4,20})\\b";
    private static String carNumberPattern = "^[A-Z]{2} ?- ?\\d{3} ?- ?[A-Z]{2}$";


    public boolean isValidModel(String name) {
        return (!name.equals("")) && name.matches(modelPattern);
    }
    public boolean isValidDateOfProduction(LocalDate date) {
        LocalDate minLimit = LocalDate.of(1800, 1, 1);
        LocalDate maxLimit = LocalDate.now();
        return date.isAfter(minLimit) && date.isBefore(maxLimit);
    }

    public boolean isValidCarNumber(String number) {
        return  (!number.equals("")) && number.matches(carNumberPattern);
    }


    public boolean isValidBodyStyle(String test) {
        if (test.equals("")) {
            return false;
        }
        for (BodyStyle bodyStyle : BodyStyle.values()) {
            if (bodyStyle.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidColor(String test) {
        if (test.equals("")) {
            return false;
        }
        for (Colors color : Colors.values()) {
            if (color.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}