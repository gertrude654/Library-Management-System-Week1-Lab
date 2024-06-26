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
    private TableColumn<Transaction, Boolean> returnedColumn;
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
        returnedColumn.setCellValueFactory(new PropertyValueFactory<>("returned"));

        setupComboBoxes();
        loadPatrons();
        loadBooks();
        refreshTable();
    }

    private void setupComboBoxes() {
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

            if (dueDate == null) {
                showAlert("Invalid Input", "Please enter valid dates.");
                return;
            }

            Transaction transaction = new Transaction(selectedPatron, selectedBook, transactionDate, returnDate, dueDate, false);
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

            ButtonType buttonTypeDelete = new ButtonType("Delete");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeDelete) {
                    transactionDao.deleteTransaction(selectedTransaction.getTransactionUd());
                    refreshTable();
                }
            });
        } else {
            showAlerts(Alert.AlertType.WARNING, "No Transaction Selected", "Please select a transaction to delete.");
        }
    }

    @FXML
    private void markTransactionAsReturned() {
        Transaction selectedTransaction = transactionTableView.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            if (!selectedTransaction.isReturned()) {
                selectedTransaction.setReturned(true);
                selectedTransaction.setReturnDate(LocalDate.now());
                transactionDao.updateTransaction(selectedTransaction);
                refreshTable();
            } else {
                showAlerts(Alert.AlertType.INFORMATION, "Already Returned", "This transaction is already marked as returned.");
            }
        } else {
            showAlerts(Alert.AlertType.WARNING, "No Transaction Selected", "Please select a transaction to mark as returned.");
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
