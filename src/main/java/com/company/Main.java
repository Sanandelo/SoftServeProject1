package com.company;


import com.company.Car.*;
import com.company.User.User;
import com.company.User.UserDAO;
import com.company.User.UsersList;
import com.company.Utilists.AdapterToJson;
import com.company.Utilists.AdapterToText;
import com.company.Utilists.AdapterToXML;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {



    public static void main(String[] args) throws IOException, JAXBException {
                   //      Create user list
//        UsersList userList = new UsersList();
//
//                        //get object which create user and car
//       ConsoleReader consoleReader = new ConsoleReader();
//
//                           //create some users from input
////
//        User user1 = consoleReader.createUser();
//        User user2 = consoleReader.createUser();
//        User user3 = consoleReader.createUser();
//
//        userList.addUser(user1);
//        userList.addUser(user2);
//
//
//        //create some cars from input
//        Car car1 = consoleReader.createCar();
//        Car car2 = consoleReader.createCar();
//        Car car3 = consoleReader.createCar();
//        user3.addCar(car1);
//        user3.addCar(car3);
//        user3.addCar(car2);
//        userList.addUser(user3);
//
//        userList.getUsers().forEach(System.out::println);








        //                          Create hardcode users
        UsersList usersList  = new UsersList();
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


                                // CREATE CARS from hardcode
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

        //                      add users to user list
        usersList.addUser(user1);
        usersList.addUser(user2);
        usersList.addUser(user3);

                 //           show list of added users
       // usersList.getUsers().forEach(System.out::println);
//

//
                        //find user by name;
        User userFromBase1 = usersList.findUserByName("Peter");
//        System.out.println(userFromBase1);


                      //find user by surname;
//        User userFromBase2 = usersList.findUserBySurname("Peterson");
//        System.out.println(userFromBase2);


               //find user by number of phone
//
//        User userFromBase3 = usersList.findUserByPhoneNumber("777-777-7777");
//        System.out.println(userFromBase3);


                //Sort users by name
        //usersList.sortByName().forEach(System.out::println);

                //Delete user by phone number;
       // usersList.deleteUserByPhoneNumber("777-777-7777");
        //usersList.getUsers().forEach(System.out::println);



            //add cars to user which was found
        userFromBase1.addCar(car1);
        userFromBase1.addCar(car2);
        userFromBase1.addCar(car3);
//        userFromBase1.getCarsOfUser().forEach(System.out::println);

        //user cars sorting
//       CarService carService = new CarService(userFromBase1.getCarsOfUser());
//       carService.sortByDataAsc();
//       carService.sortByDataDesc();
//        System.out.println(carService.findCarByNumber("BB-321-BB"));
//       carService.getCarCollection().forEach(System.out::println);








                            //read and write users to json
//       AdapterToJson adapterToJson = new AdapterToJson();

//       adapterToJson.writeUsers(usersList,"TestFile.json");
//       adapterToJson.readUsers("TestFile.json").forEach(System.out::println);
//
//
//                    //Write and read user from json
//        adapterToJson.writeUser(user1, "TestFile1.json");
//        System.out.println(adapterToJson.readUser("TestFile1.json"));




                //save users objects to xml
 //       AdapterToXML adapterToXML = new AdapterToXML();

//        adapterToXML.writeUsers(usersList,"TestFile.xml");
//        adapterToXML.readUsers("TestFile.xml").forEach(System.out::println);

                //read one user to xml.
//        adapterToXML.writeUser(userFromBase1,"TestFile1.xml");
//        User user = adapterToXML.readUser("TestFile1.xml");
//        System.out.println(user);


                //write  user to plane text
//         AdapterToText adapterToText = new AdapterToText();

//         adapterToText.writeUsers(usersList,"Test.txt");
//         adapterToText.readUsers("Test.txt").forEach(System.out::println);


                //write one user to text
//        adapterToText.writeUser(userFromBase1, "Test1.txt");
//        User user = adapterToText.readUser("Test1.txt");
//        System.out.println(user);
//

                        //Work with database

        String db_url = "jdbc:postgresql://localhost:5432/carapp";
        String user = "postgres";
        String pass = "1992";

        UserDAO userDAO = new UserDAO(db_url,user,pass);
        CarDAO carDAO = new CarDAO(db_url,user,pass);

        try {
            carDAO.dropCarTable();
            userDAO.dropUserTable();
            userDAO.createUserTable();
            carDAO.createCarTable();
            //get users id
            String id1 = user1.getId();
            String id2 = user2.getId();
            String id3 = user3.getId();
            //Insert users to db

            userDAO.insertUser(user1);
            userDAO.insertUser(user2);
            userDAO.insertUser(user3);
            System.out.println("Users after inserting");
            userDAO.listAllUsers().forEach(System.out::println);

            userDAO.hardDeleteUser(id1);
            System.out.println("Users after hard delete");
            userDAO.listAllUsers().forEach(System.out::println);


            id1 = user1.getId();
            userDAO.insertUser(user1);


            userDAO.softDeleteUser(id1);
            System.out.println("Users after soft delete");
            userDAO.listAllUsers().forEach(System.out::println);

            userDAO.restoreFromSoftDelete(id1);
            System.out.println("Users after restor from soft delete");
            userDAO.restoreFromSoftDelete(id1);
            userDAO.listAllUsers().forEach(System.out::println);





            System.out.println("Get user: ");
            System.out.println(userDAO.getUser(id3));

            System.out.println("Users sorted by name ascending");
            userDAO.sortUserByNameAscending().forEach(System.out::println);

            System.out.println("Users sorted by name descending");
            userDAO.sortUserByNameDescending().forEach(System.out::println);

            carDAO.insertCarToUser(car1,id1);
            carDAO.insertCarToUser(car2,id1);
            carDAO.insertCarToUser(car3,id3);

            System.out.println("List of all cars");
            carDAO.listAllCars().forEach(System.out::println);

            System.out.println("List cars of first user");
            carDAO.listUsersCars(id1).forEach(System.out::println);






            System.out.println("cars of user sorted by number desc ");
            carDAO.sortCarsByNumberDescending(id1).forEach(System.out::println);

            System.out.println("cars of user sorted by number desc ");
            carDAO.sortCarsByNumberAscending(id1).forEach(System.out::println);

            System.out.println("Sorting users by number of cars");
            userDAO.sortUserByAmountOfCarsDesc().forEach(System.out::println);


            System.out.println("Change owner of car");
            String number = car1.getNumber();
            carDAO.changeOwner(number,id2);
            System.out.println(carDAO.listUsersCars(id2));


            System.out.println("update car");
            car2.setColor(Colors.ORANGE);
            carDAO.updateCar(car2);
            carDAO.listAllCars().forEach(System.out::println);




            System.out.println("Hard delete car of user by id");
            carDAO.hardDeleteUserCarsById(id1);
            carDAO.listAllCars().forEach(System.out::println);

            System.out.println("Soft delte car of user by id");
            carDAO.softDeleteUserCarsById(id3);
            carDAO.listAllCars();

            System.out.println("restore from soft delete by id");

            carDAO.restoreFromSoftDelete(id3);
            carDAO.listAllCars().forEach(System.out::println);


            System.out.println("Users after update");
            user3.setId(id2);
            userDAO.updateUser(user3);
            userDAO.listAllUsers().forEach(System.out::println);



//            carDAO.dropCarTable();
//            userDAO.dropUserTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}