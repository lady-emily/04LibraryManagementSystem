module com.example.demo_jfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.demo_jfx to javafx.fxml;
    exports com.example.demo_jfx;
    opens com.example.demo_jfx.controllers to javafx.fxml;
    exports com.example.demo_jfx.controllers to javafx.fxml;
}