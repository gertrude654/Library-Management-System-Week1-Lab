package org.example.librarymanagementsystemlab.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<Transaction, Integer> patronIdColumn;
    @FXML
    private TableColumn<Transaction, Integer> bookIdColumn;
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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionUd"));
        patronIdColumn.setCellValueFactory(cellData -> {
            Patron patron = cellData.getValue().getPatronId();
            return new SimpleObjectProperty<>(patron == null ? null : patron.getPatron_id());
        });
        bookIdColumn.setCellValueFactory(cellData -> {
            Book book = cellData.getValue().getBookId();
            return new SimpleObjectProperty<>(book == null ? null : book.getBook_id());
        });
        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        refreshTable();
    }

    @FXML
    private void addTransaction() {
        try {
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

            if (transactionDate == null || returnDate == null || dueDate == null) {
                showAlert("Invalid Input", "Please enter valid dates.");
                return;
            }

            Transaction transaction = new Transaction(patron, book, transactionDate, returnDate, dueDate);
            transactionDao.addTransaction(transaction);
            refreshTable();
            clearInputFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numeric values for Patron ID and Book ID.");
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

    private void clearInputFields() {
        patronIdField.clear();
        bookIdField.clear();
        transactionDatePicker.setValue(null);
        returnDatePicker.setValue(null);
        dueDatePicker.setValue(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
