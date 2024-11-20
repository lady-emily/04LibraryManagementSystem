package com.example.demo_jfx.database;

import javafx.fxml.FXML;

import java.sql.*;

public class DatabaseConnectivity {
    // Database connection parameters
    private static final String url = "jdbc:postgresql://localhost:5432/04LibraryManagementSystem";
    private static final String username = "postgres";
    private static final String password = "korkor";

    @FXML
    public void createBookTable() throws SQLException {
        // Query: Create book table
        String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS book (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        author VARCHAR(100) NOT NULL,
                        genre VARCHAR (50),
                        published_year INT,
                        copies INT DEFAULT 1
                    )
                """;
        executeDDL(createTableSQL, "Table 'book' created or already exists.");
    }
//      try (Statement stmt = con.createStatement()) {
//            stmt.executeUpdate(createTableSQL);
//            System.out.println("Table 'book' created or already exists.");
//       }

    // Query: Create patrons table
    public void createPatronsTable() throws SQLException {
        //Query: Create patron table
        String createTableSQl = """
                    CREATE TABLE IF NOT EXISTS patrons (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        password VARCHAR(100) NOT NULL,
                        user_role VARCHAR(50) NOT NULL CHECK (user_role IN ('Librarian', 'Customer'))
                    )
                """;
        executeDDL(createTableSQl, "Table 'patrons' created or already exists.");
    }
//      try (Statement stmt = con.createStatement()) {
//            stmt.executeUpdate(createTableSQl); // for DDL
//            System.out.println("Table 'patrons' created or already exists.");
//      }

    //Query: Create transactions table
    public void createTransactionsTable() throws SQLException {
        String createTableSQL = """
                        CREATE TABLE IF NOT EXISTS transactions (
                            id SERIAL PRIMARY KEY,
                            user_id INT NOT NULL REFERENCES patrons(id) ON DELETE CASCADE,
                            book_id INT NOT NULL REFERENCES book(id) ON DELETE CASCADE,
                            borrow_date DATE NOT NULL,
                            return_date DATE,
                            fine DOUBLE PRECISION DEFAULT 0.0 CHECK (fine >=0),
                            status VARCHAR(50) DEFAULT 'Borrowed' CHECK (status IN ( 'Borrowed', 'Returned'))
                        )
                """;
        executeDDL(createTableSQL, "Table 'transactions' created or already exists.");
    }

    // Helper method for executing DDL statements
    private void executeDDL(String sql, String successMessage) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println(successMessage);
        } catch (SQLException e) {
            System.err.println("Error executing DDL: " + e.getMessage());
            throw e;
        }
    }
}
