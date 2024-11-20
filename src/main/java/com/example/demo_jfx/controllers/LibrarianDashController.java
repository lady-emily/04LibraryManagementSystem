package com.example.demo_jfx.controllers;

import com.example.demo_jfx.database.CustomerServices;
import com.example.demo_jfx.database.entity.Book;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//Initializable is an interface used for controller Initialisation
// method to override is initialize()
// initialise() method allows to work with components, containers, etc

public class LibrarianDashController implements Initializable {
    @FXML private Button view_borrowed_books;
    @FXML private AnchorPane main_pane;
    @FXML private TextField title_field;
    @FXML private TextField author_field;
    @FXML private TextField genre_field;
    @FXML private TextField year_published_field;
    @FXML private TextField price_field;
    @FXML private TextField copies_field;
    @FXML private TableView<Book> table_available_books;
    @FXML private TableColumn<Book, String> book_title, book_author, book_genre, book_year_published, book_copies;

    CustomerServices customerService;
    ObservableList<Book> bookList = FXCollections.observableArrayList();

    //Actions to perform on components to be called from the utility class
    @Override
    public void initialize(URL location, ResourceBundle resources ) {
        try {
            this.populateBookTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("This is a method");
        customerService = new CustomerServices();
//        try {
//            populateBookTable();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void handleBorrowed() throws IOException {
      main_pane.getChildren().clear(); // Clear the existing content
      FXMLLoader borrowed_loader = new FXMLLoader(getClass().getResource("/com/example/demo_jfx/borrowed_books.fxml"));
      Parent borrowedView = borrowed_loader.load(); // Load the FXML file

      main_pane.getChildren().add(borrowedView); // Add the loaded view to main_pane
    }

    public void handleAvailable() throws IOException {
        main_pane.getChildren().clear(); // Clear the existing content
        FXMLLoader available_loader = new FXMLLoader(getClass().getResource("/com/example/demo_jfx/available_view.fxml"));
        Parent borrowedView = available_loader.load(); // Load the FXML file

        main_pane.getChildren().add(borrowedView); // Add the loaded view to main_pane
    }

    public void handleManagement() throws IOException {
        main_pane.getChildren().clear(); // Clear the existing content
        FXMLLoader management_loader = new FXMLLoader(getClass().getResource("/com/example/demo_jfx/book_management.fxml"));
        Parent borrowedView = management_loader.load(); // Load the FXML file

        main_pane.getChildren().add(borrowedView); // Add the loaded view to main_pane
    }

    public void handleBookAddition() throws IOException, SQLException {
        String title = title_field.getText();
        String author = author_field.getText();
        String genre = genre_field.getText();
        int year = Integer.parseInt(year_published_field.getText());
        double price = Double.parseDouble(price_field.getText());
        int copies = Integer.parseInt(copies_field.getText());

        customerService.insertBook(title, author, genre, year,price, copies);
    }

    public void populateBookTable() throws SQLException {
        book_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        book_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        book_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        book_year_published.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        book_copies.setCellValueFactory(new PropertyValueFactory<>("copies"));


        bookList.addAll(customerService.viewBooks());
        table_available_books.setItems(bookList);
    }



}
