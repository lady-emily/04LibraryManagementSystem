package com.example.demo_jfx.database;

import java.sql.*;

//Classes for Customer Services
public class CustomerServices {
    private static final String url = "jdbc:postgresql://localhost:5432/04LibraryManagementSystem";
    private static final String username = "postgres";
    private static final String password = "korkor";

    //View all books
    public void viewBooks() throws SQLException {
        String query = "select * from books";
        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Available Books");
            while (rs.next()) {
                System.out.printf("ID: %d, Title: %s, Author: %s, Genre: %s, Copies: %d%n",
                        rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getString("genre"), rs.getInt("copies"));
            }
        }
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
}
