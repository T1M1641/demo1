module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.Controllers;
    opens com.example.demo1.Controllers to javafx.fxml;
    exports com.example.demo1.Models;
    opens com.example.demo1.Models to javafx.fxml;

}