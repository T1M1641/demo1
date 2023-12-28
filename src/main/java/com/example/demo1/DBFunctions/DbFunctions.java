package com.example.demo1.DBFunctions;

import com.example.demo1.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbFunctions {

    public Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "zxc1", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void createUser(String login, String password, String surname, String name, String patronymic, String phone, String image) {
        try {
            String query = String.format("insert into users(login, password, SurName, FirstName, LastName, Phone, image) values('%s','%s', '%s','%s','%s','%s', '%s');",
                    login, password, surname, name, patronymic, phone, image);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("User created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int check_login(String login) {
        try {
            String query = String.format("select * from users where login = '%s'", login);
            Statement statement = connect_to_db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() >= 1) {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public int signIn(String login, String password) {
        try {
            String query = String.format("select * from users where login = '%s' and password = '%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public ObservableList<User> getAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select id, firstname, lastname, login,  password from users");
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                ));
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return users;
        }
    }

}
