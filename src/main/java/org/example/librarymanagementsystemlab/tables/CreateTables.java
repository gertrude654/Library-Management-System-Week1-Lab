package org.example.librarymanagementsystemlab.tables;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {
    public static void main(String[] args) {

        try {
            // Establishing connection
            Connection conn = DatabaseConnection.getConnection();

            // SQL queries to create tables
            String createPatronTable = "CREATE TABLE IF NOT EXISTS patron (" +
                    "patron_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "first_name VARCHAR(100) NOT NULL," +
                    "last_name VARCHAR(100) NOT NULL," +
                    "username VARCHAR(100) NOT NULL UNIQUE," +
                    "password VARCHAR(100) NOT NULL)";

            String createBookTable = "CREATE TABLE IF NOT EXISTS book (" +
                    "book_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "ISBN VARCHAR(20) UNIQUE," +
                    "title VARCHAR(200)," +
                    "author VARCHAR(500)," +
                    "publication_date DATE," +
                    "category VARCHAR(100)," +
                    "is_available BOOLEAN DEFAULT TRUE)"; // Fixed missing closing parenthesis

            String createTransactionTable = "CREATE TABLE IF NOT EXISTS transaction (" +
                    "transaction_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "patron_id INT," +
                    "book_id INT," +
                    "transaction_Date DATE," +
                    "return_date DATE," +
                    "due_date DATE," +
                    "is_returned TINYINT DEFAULT 0," +
                    "FOREIGN KEY (patron_id) REFERENCES patron(patron_id)," +
                    "FOREIGN KEY (book_id) REFERENCES book(book_id))"; // Fixed missing closing parenthesis

            String createUserTable = "CREATE TABLE IF NOT EXISTS user (" +
                    "user_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(200) UNIQUE," +
                    "password VARCHAR(500))"; // Fixed missing closing parenthesis

            // Creating a Statement object instead of PreparedStatement for executing the queries
            Statement statement = conn.createStatement();

            // Executing the SQL queries to create tables
            statement.execute(createBookTable);
            statement.execute(createPatronTable);
            statement.execute(createTransactionTable);
            statement.execute(createUserTable);

            System.out.println("Tables created successfully.");

            // Closing the connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
