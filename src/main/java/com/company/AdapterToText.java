package com.company;

import com.company.Car.BodyStyle;
import com.company.Car.Car;
import com.company.Car.Colors;
import com.company.User.User;
import com.company.User.UsersList;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdapterToText implements SerializationInterface {
    @Override
    public void writeUsers(UsersList userUsers, String path) throws IOException {
        BufferedWriter file = new BufferedWriter(new FileWriter(path));
        List<User> users = userUsers.getUsers();
        for(User u : users){
            file.write(u.toString());
            file.write("\n");
        }
        file.close();
    }

    @Override
    public void writeUser(User user, String path) throws IOException {
        FileWriter file = new FileWriter(path);
        file.write(user.toString());
        file.close();

    }

    @Override
    public List<User> readUsers(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine())!=null){
            sb.append(line.trim());
            sb.append("\n");
        }
        bufferedReader.close();
        return usersParser(sb.toString());
    }

    @Override
    public User readUser(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine())!=null){
            sb.append(line.trim());
        }
        bufferedReader.close();

        return userParser(sb.toString());
    }

    //PARSING

    private User userParser(String string){
        User user = new User.UserBuilder()
                .setName(parseName(string))
                .setSurname(parseSurname(string))
                .setDatOfBirth(parseDateOfBirth(string))
                .setPhoneNumber(parsePhoneNumber(string))
                .build();
        user.setId(parseId(string));
        user.setCarsCollection(parseCarCollection(string));
        return user;
    }

    private  List<User> usersParser(String text){
        List<User> list = new ArrayList<>();

        String[] textArray = text.split("}\n");

        for(String s : textArray){
            User user = new User.UserBuilder()
                    .setName(parseName(s))
                    .setSurname(parseSurname(s))
                    .setDatOfBirth(parseDateOfBirth(s))
                    .setPhoneNumber(parsePhoneNumber(s))
                    .build();
            user.setId(parseId(s));
            user.setCarsCollection(parseCarCollection(s));
            list.add(user);

        }


    return list;
    }

    private String parseId(String text){
        String id = null;
        Pattern idPattern = Pattern.compile("Id='(.*?)'");
        Matcher matcher = idPattern.matcher(text);
        if(matcher.find()){
            id = matcher.group(1);
        }
        return id;
    }
    private String parseName(String text){
        String name = null;
        Pattern namePattern = Pattern.compile("name='(.*?)'");
        Matcher matcher = namePattern.matcher(text);
        if(matcher.find()){
            name = matcher.group(1);
        }
        return name;
    }
    private String parseSurname(String text){
        String surname = null;
        Pattern surnamePattern = Pattern.compile("surname='(.*?)'");
        Matcher matcher = surnamePattern.matcher(text);
        if(matcher.find()){
            surname = matcher.group(1);
        }
        return surname;
    }


    private LocalDate parseDateOfBirth(String text){
        LocalDate localDate = null;
        Pattern dateOfBirthPattern = Pattern.compile("dateOfBirth=(.*?),");
        Matcher matcher = dateOfBirthPattern.matcher(text);
        if(matcher.find()){
            localDate = LocalDate.parse(matcher.group(1));
        }
        return localDate;
    }

    private String parsePhoneNumber(String text){
        String phoneNumber = null;
        Pattern phoneNumberPattern = Pattern.compile("phoneNumber='(.*?)'");
        Matcher matcher = phoneNumberPattern.matcher(text);
        if(matcher.find()){
            phoneNumber = matcher.group(1);
        }
        return phoneNumber;
    }

    private List<Car> parseCarCollection(String text) {
        List<Car> list = new ArrayList<>();
        String carCollection = null;
        Pattern carCollectionPattern = Pattern.compile("carsCollection=(.*?)}]");
        Matcher matcher = carCollectionPattern.matcher(text);
        if(matcher.find()){
            carCollection = matcher.group(1);
        }
        if(carCollection==null){
            return new ArrayList<>();
        }

        String[] textArray = carCollection.split("},");
        for(String s : textArray){
            Car car = new Car.CarBuilder()
                    .setNumber(parsNumber(s))
                    .setColor(parseColor(s))
                    .setBodyStyle(parseBodyStyle(s))
                    .setModel(parseModel(s))
                    .setDateOfProduction(parseDateOfProduction(s))
                    .build();
            list.add(car);
        }
        return list;
    }

    private String parseModel(String text){
        String model = null;
        Pattern modelPattern = Pattern.compile("model='(.*?)'");
        Matcher matcher = modelPattern.matcher(text);
        if(matcher.find()){
            model = matcher.group(1);
        }
        return model;

    }

    private String parsNumber(String text){
        String number = null;
        Pattern numberPattern = Pattern.compile("number='(.*?)'");
        Matcher matcher = numberPattern.matcher(text);
        if(matcher.find()){
            number = matcher.group(1);
        }
        return number;
    }

    private Colors parseColor(String text){
        Colors color = null;
        Pattern colorPattern = Pattern.compile("color=(.*?),");
        Matcher matcher = colorPattern.matcher(text);
        if(matcher.find()){
            color = Colors.valueOf(matcher.group(1));
        }
        return color;
    }

    private BodyStyle parseBodyStyle(String text){
        BodyStyle bodyStyle = null;
        Pattern bodyStylePattern = Pattern.compile("bodyStyle=(.*?),");
        Matcher matcher = bodyStylePattern.matcher(text);
        if(matcher.find()){
           bodyStyle = BodyStyle.valueOf(matcher.group(1));
        }
        return bodyStyle;
    }

    private LocalDate parseDateOfProduction(String text){
        LocalDate localDate = null;
        Pattern dateOfProductionPattern = Pattern.compile("dateOfProduction=(.*?)\\.");
        Matcher matcher = dateOfProductionPattern.matcher(text);
        if(matcher.find()){
            localDate = LocalDate.parse(matcher.group(1));
        }
        return localDate;
    }
}
