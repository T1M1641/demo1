package com.example.demo1.DBFunctions;

import com.example.demo1.Models.Care;
import com.example.demo1.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbFunctions {

    public Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "exam", "postgres", "123");
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

    public void createUser(String firstname, String lastname, String login, String password) {
        try {
            String query = String.format("insert into users(firstname, lastname, login, password) values('%s','%s', '%s','%s');",
                    firstname, lastname, login, password);
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
            if (resultSet.next()) {
                System.out.println("");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public ObservableList<Care> getAllCares() {
        ObservableList<Care> cares = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select id, animal, nameCare from care");
            while (resultSet.next()) {
                cares.add(new Care(
                        resultSet.getString("id"),
                        resultSet.getString("animal"),
                        resultSet.getString("nameCare")
                ));
            }
            return cares;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return cares;
        }
    }

}
