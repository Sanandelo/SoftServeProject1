package com.company;


import com.company.Car.BodyStyle;
import com.company.Car.Car;
import com.company.Car.CarService;
import com.company.Car.Colors;
import com.company.User.User;
import com.company.User.UsersList;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
       //Create user db
        UsersList userDAO = new UsersList();
        //get object which create user and car
        //ConsoleReader consoleReader = new ConsoleReader();
        //create some users

//        User user1 = consoleReader.createUser();
//        User user2 = consoleReader.createUser();
//        User user3 = consoleReader.createUser();

        //create some cars
//        Car car1 = consoleReader.createCar();
//        Car car2 = consoleReader.createCar();
//        Car car3 = consoleReader.createCar();
        User user1 = new User.UserBuilder()
                .setName("George")
                .setSurname("Peterson")
                .setDatOfBirth(LocalDate.of(1950,1,1))
                .setPhoneNumber("999-999-9999")
                .build();
        User user2 = new User.UserBuilder()
                .setName("Peter")
                .setSurname("Kolin")
                .setDatOfBirth(LocalDate.of(1960,1,1))
                .setPhoneNumber("888-888-8888")
                .build();
        User user3 = new User.UserBuilder()
                .setName("Steve")
                .setSurname("Voznik")
                .setDatOfBirth(LocalDate.of(1970,1,1))
                .setPhoneNumber("777-777-7777")
                .build();

        Car car1 = new Car.CarBuilder()
                .setModel("RX-1")
                .setColor(Colors.BLACK)
                .setBodyStyle(BodyStyle.COUPE)
                .setDateOfProduction(LocalDate.of(1955,1,1))
                .setNumber("AA-123-AA")
                .build();

        Car car2 = new Car.CarBuilder()
                .setModel("RX-2")
                .setColor(Colors.RED)
                .setBodyStyle(BodyStyle.SEDAN)
                .setDateOfProduction(LocalDate.of(1963,1,1))
                .setNumber("BB-321-BB")
                .build();

        Car car3 = new Car.CarBuilder()
                .setModel("RX-3")
                .setColor(Colors.WHITE)
                .setBodyStyle(BodyStyle.LIMOUSINE)
                .setDateOfProduction(LocalDate.of(1932,1,1))
                .setNumber("CC-333-CC")
                .build();

        //add users to DB
        userDAO.addUser(user1);
        userDAO.addUser(user2);
        userDAO.addUser(user3);

        //show list of added users
        List<User> allUser = userDAO.getUsers();

        System.out.println("");

        //find user by name;
        User userFromBase1 = userDAO.findUserByName("Peter");
        //System.out.println(userFromBase1);
        //System.out.println("");

        //find user by surname;
        User userFromBase2 = userDAO.findUserBySurname("Peterson");
        //System.out.println(userFromBase2);
        //System.out.println("");

        //find user by number of phone

        User userFromBase3 = userDAO.findUserByPhoneNumber("777-777-7777");

        //add cars to user which was found
        userFromBase1.addCar(car1);
        userFromBase1.addCar(car2);
        userFromBase1.addCar(car3);

        //userFromBase1.getCarsOfUser().forEach(System.out::println);

        //user cars sorting
       CarService carService = new CarService(userFromBase1.getCarsOfUser());
       carService.sortByDataAcs();
   //    carService.getCarCollection().forEach(System.out::println);


       //adapter java to json
//       AdapterToJson adapterToJson = new AdapterToJson();
//        System.out.println("all users");
//       System.out.println(userDAO.getAllUserBase());

//       adapterToJson.usersToJson(userDAO.getAllUserBase(),"TestFile.json");
//
//       List<User> users = adapterToJson.jsonToUsers("TestFile.json");
//        System.out.println("From json: ");
//        System.out.println(users);


//        AdapterToXML adapterToXML = new AdapterToXML();
//        adapterToXML.writeUsers(userDAO,"TestFile.xml");
//        List<User> list = adapterToXML.readUsers("TestFile.xml");
//
//        adapterToXML.writeUser(user1,"TestFile1.xml");
//        User user = adapterToXML.readUser("TestFile1.xml");
//        System.out.println(user);
         AdapterToText adapterToText = new AdapterToText();
//         adapterToText.writeUsers(userDAO,"Test.txt");
//         List<User> list = adapterToText.readUsers("Test.txt");
//        System.out.println("list");
//        System.out.println(list.get(1));

        adapterToText.writeUser(userFromBase1,"Test1.txt");
        User user = adapterToText.readUser("Test1.txt");
        System.out.println(userFromBase1);
        System.out.println(user);
    }
}