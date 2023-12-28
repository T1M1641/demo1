package com.example.demo1.Controllers;

import com.example.demo1.DBFunctions.DbFunctions;
import com.example.demo1.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AuthController {

    @FXML
    private Button btnAuth;

    @FXML
    private Label labelError;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button btnReg;

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        btnAuth.setOnAction(e -> {
            validation();
        });

        btnReg.setOnAction(e -> {
            new Loader().openNewScene(root, "/com/example/demo1/reg-view.fxml", "Регистрация");
        });
    }

    private void validation() {
        String login = textFieldLogin.getText();
        String password= textFieldPassword.getText();

        int codeError = dbFunctions.signIn(login, password);

        if (login.isEmpty()){
            labelError.setText("Логин пустой!");
        } else if (password.isEmpty()) {
            labelError.setText("Пароль пустой");
        } else if(codeError == 0) {
            labelError.setText("Не найден аккаунт");
        } else if(codeError == 404) {
            labelError.setText("Какая-то ошибка");
        } else {
            labelError.setText("");
            new Loader().openNewScene(root, "/com/example/demo1/main-view.fxml", "Главный экран");
        }

    }
}
