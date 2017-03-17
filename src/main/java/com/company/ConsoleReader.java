package com.company;


import com.company.Car.*;
import com.company.User.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleReader implements UserDataInterface,CarDataInterface {
    private  Scanner sc = new Scanner(System.in);
    private  CarValidator carValidator = new CarValidator();
    private  UserValidator userValidator = new UserValidator();
    private  DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String readName() {
        String name;
        while (true) {
            System.out.println("Please type Name: ");
            String input = sc.nextLine();
            if (userValidator.isValidName(input)) {
                name = input;
                break;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
        return name;
    }
    public String readModel() {
        String model;
        while (true) {
            System.out.println("Please type Model of car: ");
            String input = sc.nextLine();
            if (carValidator.isValidModel(input)) {
                model = input;
                break;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
        return model;
    }

    public String readSurname() {
        String surname;
        while (true) {
            System.out.println("Please type Surname: ");
            String input = sc.nextLine();
            if (userValidator.isValidSurname(input)) {
                surname = input;
                break;
            } else {
                System.out.println("Invalid input. Please try again");
            }

        }
        return surname;
    }
    public LocalDate readDateOfBirth() {
        LocalDate date;
        while (true) {
            System.out.println("Please type date of birth: (dd.mm.yyyy)");
            String input = sc.nextLine();
            try {
                LocalDate uncheckedDate = LocalDate.parse(input,yearFormatter);
                if (userValidator.isValidDateOfBirth(uncheckedDate)) {
                    date = uncheckedDate;
                    break;
                } else {
                    System.out.println("Invalid input. Please try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again");
            }
        }
        return date;
    }
    public LocalDate readDateOfProduction() {
        LocalDate date;
        while (true) {
            System.out.println("Please type date of production: (dd.mm.yyyy)");
            String input = sc.nextLine();
            try {
                LocalDate uncheckedDate = LocalDate.parse(input,yearFormatter);
                if (carValidator.isValidDateOfProduction(uncheckedDate)) {
                    date = uncheckedDate;
                    break;
                } else {
                    System.out.println("Invalid input. Please try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again");
            }
        }
        return date;
    }

    public String readCarNumber() {
        String number;
        while (true) {
            System.out.println("Please type car number: (AA-333-AA)");
            String input = sc.nextLine();
            if (carValidator.isValidCarNumber(input)) {
                number = input;
                break;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
        return number;
    }

    public BodyStyle readBodyStyle() {
        BodyStyle bodyStyle;
        while (true) {
            System.out.println("Please choose car model from a list: ");
            for(BodyStyle bs : BodyStyle.values()){
                System.out.println(bs);
            }
            String input = sc.nextLine();
            input.toUpperCase();
            if (carValidator.isValidBodyStyle(input)) {
                bodyStyle = BodyStyle.valueOf(input);
                break;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
        return bodyStyle;
    }

    public Colors readColor() {
        Colors color;
        while (true) {
            System.out.println("Please choose car color from a list: ");
            for (Colors c :
                    Colors.values()) {
                System.out.println(c);
            }
            String input = sc.nextLine();
            input.toUpperCase();
            if (carValidator.isValidColor(input)) {
                color = Colors.valueOf(input);
                break;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
        return color;
    }

    public String readPhoneNumber() {
        String number;
        while (true) {
            System.out.println("Please type phone number: (999-999-9999)");
            String input = sc.nextLine();
            if (userValidator.isValidPhoneNumber(input)) {
                number = input;
                break;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
        return number;
    }

    public Car createCar(){
        return new Car.CarBuilder()
                .setModel(readModel())
                .setColor(readColor())
                .setBodyStyle(readBodyStyle())
                .setDateOfProduction(readDateOfProduction())
                .setNumber(readCarNumber())
                .build();
    }


    public User createUser(){
        return new User.UserBuilder()
                .setName(readName())
                .setSurname(readSurname())
                .setDatOfBirth(readDateOfBirth())
                .setPhoneNumber(readPhoneNumber())
                .build();
    }


}