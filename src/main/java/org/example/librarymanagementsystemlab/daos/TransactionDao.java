package org.example.librarymanagementsystemlab.daos;

import org.example.librarymanagementsystemlab.models.Transaction;

import java.util.List;

public interface TransactionDao {


    void addTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(int transactionId);

    Transaction getTransactionById(int transactionId);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByPatronId(int patronId);

    List<Transaction> getTransactionsByBookId(int bookId);

}
