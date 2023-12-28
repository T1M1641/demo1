package com.example.demo1.Controllers;

import com.example.demo1.DBFunctions.DbFunctions;
import com.example.demo1.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegController {

    @FXML
    private Button btnAuth;

    @FXML
    private Button btnReg;

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldFirstname;

    @FXML
    private TextField textFieldLastname;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private AnchorPane root;

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        btnAuth.setOnAction(e -> {
            new Loader().openNewScene(root, "/com/example/demo1/auth-view.fxml", "Авторизация");
        });

        textFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                textFieldPassword.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        btnReg.setOnAction(e -> {
            validation();
        });
    }

    private void validation() {
        String firstname = textFieldFirstname.getText();
        String lastname = textFieldLastname.getText();
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();

        int codeError = dbFunctions.check_login(login);

        if (firstname.isEmpty()) {
            labelError.setText("Заполните поле 'Фамилия'");
        } else if (lastname.isEmpty()) {
            labelError.setText("Заполните поле 'Имя'");
        } else if (login.isEmpty()) {
            labelError.setText("Заполните поле 'Логин'");
        } else if (password.isEmpty()) {
            labelError.setText("Заполните поле 'Пароль'");
        } else if (textFieldPassword.getLength() < 8) {
            labelError.setText("Пароль слишком короткий");
        } else if (codeError == 0) {
            labelError.setText("Такой логин уже существует");
        } else if (codeError == 404) {
            labelError.setText("Какая-то ошибка");
        }
        else {
            labelError.setText("");
            dbFunctions.createUser(firstname, lastname, login, password);
            new Loader().openNewScene(root, "/com/example/demo1/auth-view.fxml", "Авторизация");
        }
    }

}
