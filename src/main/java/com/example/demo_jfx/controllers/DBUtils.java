//Utility class contains just static related methods
//It is a helper class, "toolbox" for working with the database.
//Instead of writing repetitive code to connect to the database or perform queries in every part of the application
//FOr example user sign in , login, changing scenes, etc

package com.example.demo_jfx.controllers;

import com.example.demo_jfx.LibraryManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {

    //Method for changing scenes
    //Arguments include fxmlFIle of scene we want to change to,
    public static void changeScene(Stage stage, String fxmlFile, String title, String username) {
        Parent root = null; //Parent is a base class for all nodes with children
        //Customer clicks on login button
        if (username != null) {
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LogInController controller = loader.getController();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        //Customer switches between login and signup pages
        else{
            try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        //Notes
        // In JavaFX, a stage is a window of the GUI which the scene is displayed on.
        // A stage can have multiple scenes

//        Stage stage = (Stage )((Node) event.getSource()).getScene().getWindow(); //Event is the click, getSource for the source of the click,get scene and get the window
        stage.setTitle(title);
        assert root != null;
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

}
