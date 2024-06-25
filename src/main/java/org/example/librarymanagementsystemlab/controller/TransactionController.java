//package org.example.librarymanagementsystemlab.controller;
//
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.Alert.AlertType;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import org.example.librarymanagementsystemlab.daos.implementation.TransactionDaoImpl;
//import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
//import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
//import org.example.librarymanagementsystemlab.models.Book;
//import org.example.librarymanagementsystemlab.models.Patron;
//import org.example.librarymanagementsystemlab.models.Transaction;
//
//import java.time.LocalDate;
//import java.util.Stack;
//
//public class TransactionController {
//
//    @FXML
//    private TableView<Transaction> tableView;
//    @FXML
//    private TableColumn<Transaction, Integer> idColumn;
//    @FXML
//    private TableColumn<Transaction, Integer> patronIdColumn;
//    @FXML
//    private TableColumn<Transaction, Integer> bookIdColumn;
//    @FXML
//    private TableColumn<Transaction, LocalDate> transactionDateColumn;
//    @FXML
//    private TableColumn<Transaction, LocalDate> returnDateColumn;
//    @FXML
//    private TableColumn<Transaction, LocalDate> dueDateColumn;
//
//    @FXML
//    private TextField patronIdField;
//    @FXML
//    private TextField bookIdField;
//    @FXML
//    private DatePicker transactionDatePicker;
//    @FXML
//    private DatePicker returnDatePicker;
//    @FXML
//    private DatePicker dueDatePicker;
//
//    private TransactionDaoImpl transactionDao;
//    private PatronDaoImpl patronDao;
//    private BookDaoImpl bookDao;
//    private ObservableList<Transaction> transactionList;
//
//    @FXML
//    public void initialize() {
//        transactionDao = new TransactionDaoImpl();
//        patronDao = new PatronDaoImpl();
//        bookDao = new BookDaoImpl();
//
//        transactionList = FXCollections.observableArrayList();
//        tableView.setItems(transactionList);
//
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionUd"));
//        patronIdColumn.setCellValueFactory(cellData -> {
//            Patron patron = cellData.getValue().getPatronId();
//            return new SimpleObjectProperty<>(patron == null ? null : patron.getPatron_id());
//        });
//        bookIdColumn.setCellValueFactory(cellData -> {
//            Book book = cellData.getValue().getBookId();
//            return new SimpleObjectProperty<>(book == null ? null : book.getBook_id());
//        });
//        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
//        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
//        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
//
//        refreshTable();
//    }
//
//    @FXML
//    private void addTransaction() {
//        try {
//            int patronId = Integer.parseInt(patronIdField.getText());
//            Stack<Patron> patron = patronDao.getPatronById(patronId);
//            if (patron == null) {
//                showAlert("Patron Not Found", "Patron with ID " + patronId + " not found.");
//                return;
//            }
//
//            int bookId = Integer.parseInt(bookIdField.getText());
//            Book book = bookDao.getBookById(bookId);
//            if (book == null) {
//                showAlert("Book Not Found", "Book with ID " + bookId + " not found.");
//                return;
//            }
//
//            LocalDate transactionDate = transactionDatePicker.getValue();
//            LocalDate returnDate = returnDatePicker.getValue();
//            LocalDate dueDate = dueDatePicker.getValue();
//
//            if (transactionDate == null || returnDate == null || dueDate == null) {
//                showAlert("Invalid Input", "Please enter valid dates.");
//                return;
//            }
//
//            Transaction transaction = new Transaction(patron, book, transactionDate, returnDate, dueDate);
//            transactionDao.addTransaction(transaction);
//            refreshTable();
//            clearInputFields();
//        } catch (NumberFormatException e) {
//            showAlert("Invalid Input", "Please enter valid numeric values for Patron ID and Book ID.");
//        }
//    }
//
//    @FXML
//    private void deleteTransaction() {
//        Transaction selectedTransaction = tableView.getSelectionModel().getSelectedItem();
//        if (selectedTransaction != null) {
//            Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setTitle("Delete Transaction");
//            alert.setHeaderText(null);
//            alert.setContentText("Are you sure you want to delete the transaction with ID " + selectedTransaction.getTransactionUd() + "?");
//
//            // Option to confirm or cancel deletion
//            ButtonType buttonTypeDelete = new ButtonType("Delete");
//            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
//
//            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);
//
//            // Show the alert and wait for user response
//            alert.showAndWait().ifPresent(buttonType -> {
//                if (buttonType == buttonTypeDelete) {
//                    // User clicked Delete button
//                    transactionDao.deleteTransaction(selectedTransaction.getTransactionUd());
//                    refreshTable();
//                }
//            });
//        } else {
//            // No book selected in tableView
//            showAlerts(AlertType.WARNING, "No Transaction Selected", "Please select a transaction to delete.");
//        }
//    }
//
//    private void showAlerts(AlertType alertType, String title, String content) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//
//
//    private void refreshTable() {
//        transactionList.clear();
//        transactionList.addAll(transactionDao.getAllTransactions());
//    }
//
//    private void clearInputFields() {
//        patronIdField.clear();
//        bookIdField.clear();
//        transactionDatePicker.setValue(null);
//        returnDatePicker.setValue(null);
//        dueDatePicker.setValue(null);
//    }
//
//    private void showAlert(String title, String content) {
//        Alert alert = new Alert(AlertType.WARNING);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//}
package org.example.librarymanagementsystemlab.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.TransactionDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.models.Transaction;

import java.time.LocalDate;
import java.util.List;

public class TransactionController {

    @FXML
    private TableView<Transaction> transactionTableView;
    @FXML
    private TableColumn<Transaction, Integer> transactionIdColumn;
    @FXML
    private TableColumn<Transaction, Integer> patronIdColumn;
    @FXML
    private TableColumn<Transaction, Integer> bookIdColumn;
    @FXML
    private TableColumn<Transaction, LocalDate> transactionDateColumn;
    @FXML
    private TableColumn<Transaction, LocalDate> dueDateColumn;
    @FXML
    private TableColumn<Transaction, LocalDate> returnDateColumn;
    @FXML
    private ComboBox<Patron> patronComboBox;
    @FXML
    private ComboBox<Book> bookComboBox;
    @FXML
    private DatePicker transactionDatePicker;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private DatePicker returnDatePicker;

    private PatronDaoImpl patronDao;
    private BookDaoImpl bookDao;
    private TransactionDaoImpl transactionDao;
    private ObservableList<Transaction> transactionList;

    public void initialize() {
        patronDao = new PatronDaoImpl();
        bookDao = new BookDaoImpl();
        transactionDao = new TransactionDaoImpl();
        transactionList = FXCollections.observableArrayList();
        transactionTableView.setItems(transactionList);

        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionUd"));
        patronIdColumn.setCellValueFactory(cellData -> {
            Patron patron = cellData.getValue().getPatronId();
            return new SimpleObjectProperty<>(patron == null ? null : patron.getPatron_id());
        });
        bookIdColumn.setCellValueFactory(cellData -> {
            Book book = cellData.getValue().getBookId();
            return new SimpleObjectProperty<>(book == null ? null : book.getBook_id());
        });
        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        setupComboBoxes();
        loadPatrons();
        loadBooks();
        refreshTable();
    }

    private void setupComboBoxes() {
        // Setting up the Patron ComboBox to display "ID - Name"
        patronComboBox.setCellFactory(comboBox -> new ListCell<>() {
            @Override
            protected void updateItem(Patron patron, boolean empty) {
                super.updateItem(patron, empty);
                setText(empty || patron == null ? null : patron.getPatron_id() + " - " + patron.getFirstName());
            }
        });
        patronComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Patron patron, boolean empty) {
                super.updateItem(patron, empty);
                setText(empty || patron == null ? null : patron.getPatron_id() + " - " + patron.getFirstName());
            }
        });

        // Setting up the Book ComboBox to display "ID - Title"
        bookComboBox.setCellFactory(comboBox -> new ListCell<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                setText(empty || book == null ? null : book.getBook_id() + " - " + book.getTitle());
            }
        });
        bookComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                setText(empty || book == null ? null : book.getBook_id() + " - " + book.getTitle());
            }
        });
    }

    private void loadPatrons() {
        List<Patron> patrons = patronDao.listAllPatrons();
        patronComboBox.setItems(FXCollections.observableArrayList(patrons));
    }

    private void loadBooks() {
        List<Book> books = bookDao.listAllBooks();
        bookComboBox.setItems(FXCollections.observableArrayList(books));
    }

    @FXML
    private void addTransaction() {
        try {
            Patron selectedPatron = patronComboBox.getSelectionModel().getSelectedItem();
            Book selectedBook = bookComboBox.getSelectionModel().getSelectedItem();

            if (selectedPatron == null) {
                showAlert("Patron Not Selected", "Please select a patron.");
                return;
            }

            if (selectedBook == null) {
                showAlert("Book Not Selected", "Please select a book.");
                return;
            }

            LocalDate transactionDate = transactionDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            LocalDate dueDate = dueDatePicker.getValue();

            if (transactionDate == null || returnDate == null || dueDate == null) {
                showAlert("Invalid Input", "Please enter valid dates.");
                return;
            }

            Transaction transaction = new Transaction(selectedPatron, selectedBook, transactionDate, returnDate, dueDate);
            transactionDao.addTransaction(transaction);
            refreshTable();
            clearInputFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numeric values for Patron ID and Book ID.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void deleteTransaction() {
        Transaction selectedTransaction = transactionTableView.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Transaction");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the transaction with ID " + selectedTransaction.getTransactionUd() + "?");

            // Option to confirm or cancel deletion
            ButtonType buttonTypeDelete = new ButtonType("Delete");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

            // Show the alert and wait for user response
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeDelete) {
                    // User clicked Delete button
                    transactionDao.deleteTransaction(selectedTransaction.getTransactionUd());
                    refreshTable();
                }
            });
        } else {
            // No book selected in tableView
            showAlerts(Alert.AlertType.WARNING, "No Transaction Selected", "Please select a transaction to delete.");
        }
    }

    private void showAlerts(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void refreshTable() {
        transactionList.clear();
        transactionList.addAll(transactionDao.getAllTransactions());
    }

    private void clearInputFields() {
        patronComboBox.getSelectionModel().clearSelection();
        bookComboBox.getSelectionModel().clearSelection();
        transactionDatePicker.setValue(null);
        dueDatePicker.setValue(null);
        returnDatePicker.setValue(null);
    }
}
