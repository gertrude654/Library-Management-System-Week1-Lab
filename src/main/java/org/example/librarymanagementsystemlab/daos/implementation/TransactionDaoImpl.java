package org.example.librarymanagementsystemlab.daos.implementation;

import org.example.librarymanagementsystemlab.daos.TransactionDao;
import org.example.librarymanagementsystemlab.models.Transaction;
import org.example.librarymanagementsystemlab.tables.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    //private Connection conn;


    @Override
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (patron_id, book_id, transaction_date, due_date, return_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, transaction.getPatronId().getPatron_id());
            stmt.setInt(2, transaction.getBookId().getBook_id());
            stmt.setDate(3, Date.valueOf(transaction.getTransactionDate()));
            stmt.setDate(4, Date.valueOf(transaction.getDueDate()));
            stmt.setObject(5, transaction.getReturnDate() != null ? Date.valueOf(transaction.getReturnDate()) : null);


//            int affectedRows = stmt.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("Creating transaction failed, no rows affected.");
//            }
//
//            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    transaction.setTransactionUd(generatedKeys.getInt(1));
//                } else {
//                    throw new SQLException("Creating transaction failed, no ID obtained.");
//                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
//        String sql = "UPDATE transaction SET patron_id=?, book_id=?, transaction_date=?, " +
//                "due_date=?, return_date=?, has_fine=? WHERE transaction_id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, transaction.getPatronId().getPatron_id());
//            stmt.setInt(2, transaction.getBookId().getbook_id());
//            stmt.setDate(3, Date.valueOf(transaction.getTransactionDate()));
//            stmt.setDate(4, Date.valueOf(transaction.getDueDate()));
//            stmt.setObject(5, transaction.getReturnDate() != null ? Date.valueOf(transaction.getReturnDate()) : null);
//            stmt.setBoolean(6, transaction.isHasFine());
//            stmt.setInt(7, transaction.getTransactionUd());
//
//            int affectedRows = stmt.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("Updating transaction failed, no rows affected.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions appropriately
//        }
    }

    @Override
    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transaction WHERE transaction_id=?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, transactionId);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected != 0){
                System.out.println("transaction deleted successfully.");

            } else {
                System.out.println("transaction not updated");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
//        String sql = "SELECT * FROM transaction WHERE transaction_id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, transactionId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return extractTransactionFromResultSet(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions appropriately
//        }
       return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction";
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Transaction trans = new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getObject("patronId"),
                        rs.getObject("bookId"),
                        rs.getDate("transactionDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate(),
                        rs.getDate("dueDate").toLocalDate()

                        );
                transactions.add(trans);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByPatronId(int patronId) {
//        List<Transaction> transactions = new ArrayList<>();
//        String sql = "SELECT * FROM transaction WHERE patron_id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, patronId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Transaction transaction = extractTransactionFromResultSet(rs);
//                transactions.add(transaction);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions appropriately
//        }
//        return transactions;
        return null;

    }

    @Override
    public List<Transaction> getTransactionsByBookId(int bookId) {
//        List<Transaction> transactions = new ArrayList<>();
//        Transaction transactionss = new Transaction();
//        String sql = "SELECT * FROM transaction WHERE book_id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, bookId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Transaction transaction = new Transaction(
//                        rs.getInt("transcation_id"),
//                        rs.getObject(1),
//                        rs.getObject(1),
//                        rs.getDate("transaction_date"),
//                        rs.getDate("return_date"),
//                        rs.getDate("due_date"),
//                        rs.getBoolean("has_fine")
//
//                );
//                transactions.add(transaction);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions appropriately
//        }
//        return transactions;
        return null;
    }




}
