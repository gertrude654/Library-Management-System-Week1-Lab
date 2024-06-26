package org.example.librarymanagementsystemlab.daos;

import org.example.librarymanagementsystemlab.models.Transaction;

import java.util.List;

public interface TransactionDao {


    void addTransaction(Transaction transaction);

    void deleteTransaction(int transactionId);

    List<Transaction> getAllTransactions();
     void markAsReturned(int transactionId);
    void updateTransaction(Transaction transaction);




}
