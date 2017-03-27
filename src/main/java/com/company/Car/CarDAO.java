package com.company.Car;

import com.company.Car.BodyStyle;
import com.company.Car.Car;
import com.company.Car.Colors;
import com.company.User.User;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public CarDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    public boolean createCarTable() throws SQLException {
        String sql = "CREATE TABLE cars(" +
                "number varchar(255) PRIMARY KEY NOT NULL," +
                "model varchar(255)," +
                "color varchar(255)," +
                "body_style varchar(255)," +
                "date_of_production date," +
                "user_id varchar(255) references users(id)," +
                "is_delete boolean DEFAULT FALSE" +
                ");";
        connect();

        Statement statement = jdbcConnection.createStatement();
        boolean tableCreated = statement.executeUpdate(sql)>0;
        statement.close();
        disconnect();
        return tableCreated;
    }

    public boolean dropCarTable() throws SQLException {
        String sql = "DROP TABLE cars;";

        connect();

        Statement statement = jdbcConnection.createStatement();
        boolean tableDropped = statement.executeUpdate(sql) > 0;
        statement.close();
        disconnect();
        return tableDropped;
    }

    public List<Car> listAllCars() throws SQLException {
        List<Car> listCars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String number = resultSet.getString("number");
            String model = resultSet.getString("model");
            Colors color = Colors.valueOf(resultSet.getString("color"));
            BodyStyle bodyStyle = BodyStyle.valueOf(resultSet.getString("body_style"));
            LocalDate dateOfProduction = resultSet.getDate("date_of_production").toLocalDate();
            Car car = new Car.CarBuilder().setNumber(number).setModel(model).setColor(color).setBodyStyle(bodyStyle).setDateOfProduction(dateOfProduction).build();
            listCars.add(car);
        }
        resultSet.close();
        statement.close();

        disconnect();
        return listCars;
    }

    public List<Car> listUsersCars(String id ) throws SQLException {
        List<Car> listCars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE user_id = ?;";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String number = resultSet.getString("number");
            String model = resultSet.getString("model");
            Colors color = Colors.valueOf(resultSet.getString("color"));
            BodyStyle bodyStyle = BodyStyle.valueOf(resultSet.getString("body_style"));
            LocalDate dateOfProduction = resultSet.getDate("date_of_production").toLocalDate();
            Car car = new Car.CarBuilder().setNumber(number).setModel(model).setColor(color).setBodyStyle(bodyStyle).setDateOfProduction(dateOfProduction).build();
            listCars.add(car);
        }
        resultSet.close();
        statement.close();
        return listCars;
    }
    public boolean insertCarToUser(Car car, String user_id) throws SQLException {
        String sql = "INSERT INTO cars (number, model, color, body_style, date_of_production, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,car.getNumber());
        statement.setString(2,car.getModel());
        statement.setString(3,car.getColor().toString());
        statement.setString(4,car.getBodyStyle().toString());
        statement.setDate(5,Date.valueOf(car.getDateOfProduction()));
        statement.setString(6,user_id);

        boolean rowInserted = statement.executeUpdate()>0;

        statement.close();
        disconnect();

        return rowInserted;
    }
    public boolean hardDeleteUserCarsById(String id) throws SQLException {
        String sql = "DELETE FROM cars WHERE user_id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean softDeleteUserCarsById(String id) throws SQLException {
        String sql = "UPDATE cars SET is_delete = true WHERE user_id = ?;";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean restoreFromSoftDelete(String id) throws SQLException {
        String sql = "UPDATE Cars SET is_delete = false WHERE user_id = ?;";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        boolean rowRestored = statement.executeUpdate() > 0;
        statement.close();
        return rowRestored;
    }

    public boolean changeOwner(String number, String newOwnerId) throws SQLException {
        String sql = "UPDATE cars SET user_id = ? WHERE number = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, newOwnerId);
        statement.setString(2, number);

        boolean ownerChanged = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return ownerChanged;
    }

    public boolean updateCar(Car car) throws SQLException {
        String sql = "UPDATE cars SET  model = ?, color = ? , body_style = ? , date_of_production =?  WHERE number = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, car.getModel());
        statement.setString(2, car.getColor().toString());
        statement.setString(3, car.getBodyStyle().toString());
        statement.setDate(4, Date.valueOf(car.getDateOfProduction()));
        statement.setString(5, car.getNumber());

        boolean colorChanged = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return colorChanged;
    }

    public List<Car> sortCarsByNumberAscending(String id) throws SQLException {
        List<Car> listCars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE user_id = ? ORDER BY number ASC";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String number = resultSet.getString("number");
            String model = resultSet.getString("model");
            Colors color = Colors.valueOf(resultSet.getString("color"));
            BodyStyle bodyStyle = BodyStyle.valueOf(resultSet.getString("body_style"));
            LocalDate dateOfProduction = resultSet.getDate("date_of_production").toLocalDate();
            Car car = new Car.CarBuilder().setNumber(number).setModel(model).setColor(color).setBodyStyle(bodyStyle).setDateOfProduction(dateOfProduction).build();
            listCars.add(car);
        }
        resultSet.close();
        statement.close();
        return listCars;
    }
    public List<Car> sortCarsByNumberDescending(String id) throws SQLException {
        List<Car> listCars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE user_id = ? ORDER BY number DESC";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String number = resultSet.getString("number");
            String model = resultSet.getString("model");
            Colors color = Colors.valueOf(resultSet.getString("color"));
            BodyStyle bodyStyle = BodyStyle.valueOf(resultSet.getString("body_style"));
            LocalDate dateOfProduction = resultSet.getDate("date_of_production").toLocalDate();
            Car car = new Car.CarBuilder().setNumber(number).setModel(model).setColor(color).setBodyStyle(bodyStyle).setDateOfProduction(dateOfProduction).build();
            listCars.add(car);
        }
        resultSet.close();
        statement.close();
        return listCars;
    }
}
