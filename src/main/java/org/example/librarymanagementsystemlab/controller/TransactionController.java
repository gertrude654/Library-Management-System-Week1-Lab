package org.example.librarymanagementsystemlab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.example.librarymanagementsystemlab.daos.implementation.TransactionDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.models.Transaction;

import java.time.LocalDate;

public class TransactionController {

    @FXML
    private TableView<Transaction> tableView;
    @FXML
    private TableColumn<Transaction, Integer> idColumn;
    @FXML
    private TableColumn<Transaction, Patron> patronIdColumn;
    @FXML
    private TableColumn<Transaction, Book> bookIdColumn;
    @FXML
    private TableColumn<Transaction, LocalDate> transactionDateColumn;
    @FXML
    private TableColumn<Transaction, LocalDate> returnDateColumn;
    @FXML
    private TableColumn<Transaction, LocalDate> dueDateColumn;

    @FXML
    private TextField patronIdField;
    @FXML
    private TextField bookIdField;
    @FXML
    private DatePicker transactionDatePicker;
    @FXML
    private DatePicker returnDatePicker;
    @FXML
    private DatePicker dueDatePicker;

    private TransactionDaoImpl transactionDao;
    private PatronDaoImpl patronDao;
    private BookDaoImpl bookDao;
    private ObservableList<Transaction> transactionList;

    @FXML
    public void initialize() {
        transactionDao = new TransactionDaoImpl();
        patronDao = new PatronDaoImpl();
        bookDao = new BookDaoImpl();

        transactionList = FXCollections.observableArrayList();
        tableView.setItems(transactionList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        patronIdColumn.setCellValueFactory(new PropertyValueFactory<>("patron"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        refreshTable();
    }

    @FXML
    private void addTransaction() {
        int patronId = Integer.parseInt(patronIdField.getText());
        Patron patron = patronDao.getPatronById(patronId);
        if (patron == null) {
            showAlert("Patron Not Found", "Patron with ID " + patronId + " not found.");
            return;
        }

        int bookId = Integer.parseInt(bookIdField.getText());
        Book book = bookDao.getBookById(bookId);
        if (book == null) {
            showAlert("Book Not Found", "Book with ID " + bookId + " not found.");
            return;
        }

        LocalDate transactionDate = transactionDatePicker.getValue();
        LocalDate returnDate = returnDatePicker.getValue();
        LocalDate dueDate = dueDatePicker.getValue();

        Transaction transaction = new Transaction(patron, book, transactionDate, returnDate, dueDate);
        transactionDao.addTransaction(transaction);
        refreshTable();
    }

    @FXML
    private void updateTransaction() {
        Transaction selectedTransaction = tableView.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            int patronId = Integer.parseInt(patronIdField.getText());
            Patron patron = patronDao.getPatronById(patronId);
            if (patron == null) {
                showAlert("Patron Not Found", "Patron with ID " + patronId + " not found.");
                return;
            }

            int bookId = Integer.parseInt(bookIdField.getText());
            Book book = bookDao.getBookById(bookId);
            if (book == null) {
                showAlert("Book Not Found", "Book with ID " + bookId + " not found.");
                return;
            }

            LocalDate transactionDate = transactionDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            LocalDate dueDate = dueDatePicker.getValue();

            selectedTransaction.setPatronId(patron);
            selectedTransaction.setBookId(book);
            selectedTransaction.setTransactionDate(transactionDate);
            selectedTransaction.setReturnDate(returnDate);
            selectedTransaction.setDueDate(dueDate);

            transactionDao.updateTransaction(selectedTransaction);
            refreshTable();
        }
    }

    @FXML
    private void deleteTransaction() {
        Transaction selectedTransaction = tableView.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            transactionDao.deleteTransaction(selectedTransaction.getTransactionUd());
            refreshTable();
        }
    }

    private void refreshTable() {
        transactionList.clear();
        transactionList.addAll(transactionDao.getAllTransactions());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
