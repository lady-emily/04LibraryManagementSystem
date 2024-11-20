package com.example.demo_jfx.database;

import com.example.demo_jfx.database.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Classes for Customer Services
public class CustomerServices {
    private static final String url = "jdbc:postgresql://localhost:5432/04LibraryManagementSystem";
    private static final String username = "postgres";
    private static final String password = "korkor";

    //View all books
    public List<Book> viewBooks() throws SQLException {
        List<Book> booksList = new ArrayList();

        String query = "select * from books";
        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Available Books");
            while (rs.next()) {
                System.out.printf("ID: %d, Title: %s, Author: %s, Genre: %s, Copies: %d%n",
                        rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("genre"), rs.getInt("copies"));

                //This function populates the books attributes in the sceneBuilder for an instance
                Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getInt("published_year"), rs.getInt("copies"));

                booksList.add(book);
            }
        }
        return booksList;
    }

    // Search books by criteria
    public void searchBooks(String criteria, String value) throws SQLException {
        String query = "SELECT * FROM book WHERE " + criteria + " ILIKE ?";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, "%" + value + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Search Results:");
                while (rs.next()) {
                    System.out.printf("ID: %d, Title: %s, Author: %s, Genre: %s%n",
                            rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                            rs.getString("genre"));
                }
            }
        }
    }


    //Librarian Service class
    public class LibrarianService {
        private static final String url = "jdbc:postgresql://localhost:5432/04LibraryManagementSystem";
        private static final String username = "postgres";
        private static final String password = "korkor";

        // Confirm availability of a book
        public boolean isBookAvailable(int bookId) throws SQLException {
            String query = "SELECT copies FROM book WHERE id = ?";
            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, bookId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("copies") > 0;
                    }
                }
            }
            return false;
        }
    }

    public void insertBook(String title, String author, String genre, int published_year, double price, int copies ) throws SQLException {
        String sql = "INSERT INTO book" + " (title, author, genre, published_year, price, copies) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, genre);
            stmt.setInt(4, published_year);
            stmt.setDouble(5, price);
            stmt.setInt(6, copies);

            int status = stmt.executeUpdate();
            if (status > 0) {
                System.out.println(" Book Inserted");
            }
        }
    }

}