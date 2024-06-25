package org.example.librarymanagementsystemlab.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateTables {
    public static void main(String[] args) {

        try {
            Connection conn = DatabaseConnection.getConnection();
            String createPatronTable = "CREATE TABLE IF NOT EXISTS patron (" +
                    "patron_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "first_name VARCHAR(100) NOT NULL," +
                    "last_name VARCHAR(100) NOT NULL," +
                    "dob DATE NOT NULL)";


            String createBookTable = "CREATE TABLE IF NOT EXISTS book (" +
                    "book_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "ISBN VARCHAR(20) UNIQUE," +
                    "title VARCHAR(200)," +
                    "author VARCHAR(500)," +
                    "publication_date DATE," +
                    "category VARCHAR(100)," +
                    "quantity INT)" ;


            String createTransactionTable = "CREATE TABLE IF NOT EXISTS transaction (" +
                    "transaction_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "patron_id INT," +
                    "book_id INT," +
                    "transaction_Date DATE ," +
                    "return_date DATE," +
                    "due_date DATE," +
                    "FOREIGN KEY (patron_id) REFERENCES patron(patron_id)," +
                    "FOREIGN KEY (book_id) REFERENCES book(book_id))";

            PreparedStatement preparedStatement = conn.prepareStatement(("?,?,?"));

            preparedStatement.execute(createBookTable);
            preparedStatement.execute(createPatronTable);
            preparedStatement.execute(createTransactionTable);

            System.out.println("Tables created successfully.");

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
