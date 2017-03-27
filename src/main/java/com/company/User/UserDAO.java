package com.company.User;
import com.company.Car.BodyStyle;
import com.company.Car.Car;
import com.company.Car.CarDAO;
import com.company.Car.Colors;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertUser(User user) throws  SQLException{
        String sql = "INSERT INTO users (id, name, surname, date_of_birth, phone_number) " +
                "VALUES (?, ?, ?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,user.getId());
        statement.setString(2,user.getName());
        statement.setString(3,user.getSurname());
        statement.setDate(4,Date.valueOf(user.getDateOfBirth()));
        statement.setString(5,user.getPhoneNumber());
        boolean rowInserted = statement.executeUpdate()>0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException{
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE is_delete = false;";
        connect();

        CarDAO carDAO = new CarDAO(jdbcURL,jdbcUsername,jdbcPassword);

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name  = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate localDate = resultSet.getDate("date_of_birth").toLocalDate();
            String phoneNumber = resultSet.getString("phone_number");
            User user = new User.UserBuilder().setName(name).setSurname(surname).setDatOfBirth(localDate).setPhoneNumber(phoneNumber).build();
            user.setId(id);
            user.setCarsCollection(carDAO.listUsersCars(id));
            listUser.add(user);
        }
        resultSet.close();
        statement.close();

        disconnect();
        return listUser;
    }

    public boolean hardDeleteUser(String id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean softDeleteUser(String id) throws SQLException {
        String sql = "UPDATE users SET is_delete = true WHERE id = ?;";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        boolean rowDelete = statement.executeUpdate() > 0;
        statement.close();
        return rowDelete;
    }

    public boolean restoreFromSoftDelete(String id) throws SQLException {
        String sql = "UPDATE users SET is_delete = false WHERE id = ?;";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        boolean rowRestored = statement.executeUpdate() > 0;
        statement.close();
        return rowRestored;
    }

    public boolean updateUser( User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, surname = ?, date_of_birth = ?, phone_number = ?" +
                " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setDate(3,Date.valueOf(user.getDateOfBirth()));
        statement.setString(4,user.getPhoneNumber());
        statement.setString(5, user.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public User getUser(String id ) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        connect();

        CarDAO carDAO = new CarDAO(jdbcURL,jdbcUsername,jdbcPassword);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            String name  = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate localDate = resultSet.getDate("date_of_birth").toLocalDate();
            String phoneNumber = resultSet.getString("phone_number");
            user = new User.UserBuilder().setName(name).setSurname(surname).setDatOfBirth(localDate).setPhoneNumber(phoneNumber).build();
            user.setId(id);
            user.setCarsCollection(carDAO.listUsersCars(id));
        }
        resultSet.close();
        statement.close();
            return user;
    }


    public boolean createUserTable() throws SQLException {
        String sql = "CREATE TABLE users(" +
                "id varchar(255) PRIMARY KEY NOT NULL, " +
                "name varchar(255) NOT NULL, " +
                "surname varchar(255), " +
                "date_of_birth date, " +
                "phone_number varchar(255), " +
                "is_delete boolean DEFAULT FALSE" +
                ");";
            connect();

            Statement statement = jdbcConnection.createStatement();
            boolean tableCreated = statement.executeUpdate(sql)>0;
            statement.close();
            disconnect();
            return tableCreated;
    }


    public boolean dropUserTable() throws SQLException {
        String sql = "DROP TABLE users;";

        connect();

        Statement statement = jdbcConnection.createStatement();
        boolean tableDropped = statement.executeUpdate(sql) > 0;
        statement.close();
        disconnect();
        return tableDropped;
    }

    public List<User> sortUserByNameAscending() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY name ASC";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name  = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate localDate = resultSet.getDate("date_of_birth").toLocalDate();
            String phoneNumber = resultSet.getString("phone_number");
            User user = new User.UserBuilder().setName(name).setSurname(surname).setDatOfBirth(localDate).setPhoneNumber(phoneNumber).build();
            user.setId(id);
            listUser.add(user);
        }
        resultSet.close();
        statement.close();

        disconnect();
        return listUser;
    }
    public List<User> sortUserByNameDescending() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY name DESC";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate localDate = resultSet.getDate("date_of_birth").toLocalDate();
            String phoneNumber = resultSet.getString("phone_number");
            User user = new User.UserBuilder().setName(name).setSurname(surname).setDatOfBirth(localDate).setPhoneNumber(phoneNumber).build();
            user.setId(id);
            listUser.add(user);
        }
        resultSet.close();
        statement.close();

        disconnect();
        return listUser;


    }

    public List<User> sortUserByAmountOfCarsAsc() throws SQLException {
        List<User> listUser = listAllUsers();
        listUser.sort((user1, user2) -> user1.getCarsOfUser().size() - user2.getCarsOfUser().size());
        return listUser;
    }
    public List<User> sortUserByAmountOfCarsDesc() throws SQLException {
        List<User> listUser = listAllUsers();
        listUser.sort((user1, user2) -> user2.getCarsOfUser().size() - user1.getCarsOfUser().size());
        return listUser;
    }

    public List<User> sortUserByAmountOfCars2() throws SQLException{
        return null;
    }
}


