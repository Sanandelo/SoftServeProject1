package com.company.User;
import com.company.Car.Car;

import java.sql.*;
import java.time.LocalDate;

public class UserDatabase {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/carapp";

    static final String USER = "postgres";
    static final String PASS = "1992";


    public Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createUserTable(){
        String sql = "CREATE TABLE users(" +
                "id varchar(255) PRIMARY KEY NOT NULL, " +
                "name varchar(255) NOT NULL, " +
                "surname varchar(255), " +
                "date_of_birth date, " +
                "phone_number varchar(255)" +
                ");";
        Connection connection = createConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUserTable(){
        String sql = "DROP TABLE users;";
        Connection connection = createConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void showTabels(){
        String sql = "";
    }
    public boolean addUser(User user){
        Statement statement = null;
        boolean result = false;
        //(id, name, surname, date_of_birth, phone_number)
        String sql = "INSERT INTO users " +
                "VALUES(\'"+ user.getId() + "\', \'" +
                user.getName() + "\', \'" +
                user.getSurname() + "\', \'" +
                Date.valueOf(user.getDateOfBirth()) + "\', \'" +
                user.getPhoneNumber() + "\');";
        System.out.println(sql);
            Connection connection = createConnection();
        try {
            statement = connection.createStatement();
            result = statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean addCarToUser(Car car, User user){
    return false;
    }

    public void showAllUsers(){
        Statement statement = null;
        String sql = "SELECT * FROM users";

        Connection connection = createConnection();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String id = resultSet.getString("id");
                String name  = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                LocalDate localDate = resultSet.getDate("date_of_Birth").toLocalDate();
                String phoneNumber = resultSet.getString("phone_number");
                //TODO Cars list

                System.out.print(id);
                System.out.println(name);
                System.out.println(surname);
                System.out.println(localDate);
                System.out.println(phoneNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
