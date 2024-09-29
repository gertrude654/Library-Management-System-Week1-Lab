package org.example.librarymanagementsystemlab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.models.Transaction;

public class PatronHomeController {

    @FXML
    private TableView<Book> availableBooksTable;
    @FXML
    private TableView<Transaction> myTransactionsTable;

    private Patron loggedInPatron;

    public void setPatron(Patron patron) {
        this.loggedInPatron = patron;
        loadPatronTransactions();
        loadAvailableBooks();
    }

    private void loadPatronTransactions() {
        // Implement logic to load and display transactions for this patron
    }

    private void loadAvailableBooks() {
        // Implement logic to load and display available books
    }
}
