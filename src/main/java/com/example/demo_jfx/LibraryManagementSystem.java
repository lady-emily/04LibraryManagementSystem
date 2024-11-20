package com.example.demo_jfx;

import com.example.demo_jfx.controllers.LibrarianDashController;
import com.example.demo_jfx.database.DatabaseConnectivity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryManagementSystem extends Application {

    public static void main(String[] args) {
        //Initialising database and table creation
        try{
            DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
            databaseConnectivity.createBookTable();
            databaseConnectivity.createPatronsTable();
            databaseConnectivity.createTransactionsTable();
//            LibrarianDashController librarianDashController = new LibrarianDashController();
//            librarianDashController.populateBookTable();
        }
        catch (Exception e) {
            System.err.println("An error occurred while running the database: " + e.getMessage());
            e.printStackTrace();
        }
        //Launching JavaFX app
        launch(args);
    }

    // Run the DatabaseConnectivity logic
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(LibraryManagementSystem.class.getResource("login-view.fxml"));
        Parent root = loader.load();

        // Create a Scene and set it in Stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My LibraryManagement App");
        primaryStage.show();
    }

    private static void showLibrarianDashboard() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LibraryManagementSystem.class.getResource("dashboard-view.fxml"));
    }
}

