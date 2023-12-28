package com.example.demo1.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo1.DBFunctions.DbFunctions;
import com.example.demo1.Models.Care;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TableColumn<?, ?> columnAnimal;

    @FXML
    private TableColumn<?, ?> columnCare;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableView<Care> tableView;


    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnAnimal.setCellValueFactory(new PropertyValueFactory<>("animal"));
        columnCare.setCellValueFactory(new PropertyValueFactory<>("nameCare"));
        tableView.setItems(dbFunctions.getAllCares());

    }

}
