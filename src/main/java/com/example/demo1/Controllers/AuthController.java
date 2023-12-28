package com.example.demo1.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AuthController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}