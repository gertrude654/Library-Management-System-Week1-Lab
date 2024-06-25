package org.example.librarymanagementsystemlab.daos.implementation;

import org.example.librarymanagementsystemlab.daos.TransactionDao;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.models.Transaction;
import org.example.librarymanagementsystemlab.tables.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public void addTransaction(Transaction transaction) {

        String sql = "INSERT INTO transaction (patron_id, book_id, transaction_date, due_date, return_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Check if the patron_id exists
            if (!recordExists(conn, "patron", "patron_id", transaction.getPatronId().getPatron_id())) {
                System.out.println("Patron ID " + transaction.getPatronId().getPatron_id() + " does not exist.");
                return;
            }

            // Check if the book_id exists
            if (!recordExists(conn, "book", "book_id", transaction.getBookId().getBook_id())) {
                System.out.println("Book ID " + transaction.getBookId().getBook_id() + " does not exist.");
                return;
            }

            // Check if book quantity is greater than 0
            if (getBookQuantity(transaction.getBookId().getBook_id()) <= 0) {
                System.out.println("Book quantity is 0. Cannot check out.");
                return;
            }

            // Set parameters for the prepared statement
            stmt.setInt(1, transaction.getPatronId().getPatron_id());
            stmt.setInt(2, transaction.getBookId().getBook_id());
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setDate(4, java.sql.Date.valueOf(transaction.getDueDate()));
            stmt.setDate(5, java.sql.Date.valueOf(transaction.getReturnDate()));

            // Execute the update
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Update book quantity after checkout
                updateBookQuantity(transaction.getBookId().getBook_id(), -1);
                System.out.println("Book checked out successfully.");
            } else {
                System.out.println("Failed to check out the book after transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately, such as logging, showing an alert, or throwing a custom exception
        }
    }

    @Override
    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transaction WHERE transaction_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transactionId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction deleted successfully.");
            } else {
                System.out.println("Transaction not deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT t.transaction_id, t.patron_id, t.book_id, t.transaction_date, t.return_date, t.due_date, " +
                "       p.patron_id AS patron_id, b.book_id AS book_id " +
                "FROM transaction t " +
                "JOIN patron p ON t.patron_id = p.patron_id " +
                "JOIN book b ON t.book_id = b.book_id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                int patronId = rs.getInt("patron_id");
                int bookId = rs.getInt("book_id");
                LocalDate transactionDate = rs.getDate("transaction_date").toLocalDate();
                LocalDate returnDate = rs.getDate("return_date").toLocalDate();
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();

                Patron patron = new Patron(patronId);
                Book book = new Book(bookId);

                Transaction trans = new Transaction(
                        transactionId,
                        patron,
                        book,
                        transactionDate,
                        returnDate,
                        dueDate
                );
                transactions.add(trans);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private boolean recordExists(Connection conn, String tableName, String columnName, int id) {
        String checkSql = "SELECT 1 FROM " + tableName + " WHERE " + columnName + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(checkSql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateBookQuantity(int bookId, int quantityChange) {
        // Ensure quantityChange is within reasonable bounds
        if (quantityChange == 0) {
            System.out.println("No change in book quantity.");
            return;
        } else if (quantityChange < 0) {
            // Check if reducing quantity will not drop below 0
            if (getBookQuantity(bookId) <= 0) {
                System.out.println("Book quantity is already 0. Cannot decrease further.");
                return;
            }
        }

        String sql;
        if (quantityChange > 0) {
            sql = "UPDATE book SET quantity = quantity + ? WHERE book_id = ?";
        } else {
            sql = "UPDATE book SET quantity = CASE WHEN quantity > 0 THEN quantity + ? ELSE quantity END WHERE book_id = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantityChange);
            stmt.setInt(2, bookId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book quantity updated successfully.");
            } else {
                System.out.println("Failed to update book quantity.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getBookQuantity(int bookId) {
        String sql = "SELECT quantity FROM book WHERE book_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if book not found or error occurs
    }
}
