package com.example.demo_jfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowedBooks {

//Initializable is an interface used for controller Initialisation
// method to override is initialize()
// initialise() method allows to work with components, containers, etc

    public class BorrwedBooks implements Initializable {

        // initialise components
        @FXML
        private Button button_login;

    @FXML
    TextField tf_password;
    @FXML
    TextField tf_email;
    @FXML
    RadioButton radio_librarian;
    @FXML
    ActionEvent actionEvent;

    //Actions to perform on components to be called from the utility class
    @Override
    public void initialize(URL location, ResourceBundle resources ) {

    }

    public void handleLogin(){
        // TODO 1: Database query
        if(tf_email.getText().equals("admin@gmail.com") && tf_password.getText().equals("admin")){
//            if(radio_librarian.isSelected()){
            System.out.println("You are logged in");
            DBUtils.changeScene((Stage) button_login.getScene().getWindow(), "/com/example/demo_jfx/librarian_dash.fxml", "Librarian Dashboard", tf_email.getText());
        }else{
            System.out.println("You are not logged in");
        }
    }
}
