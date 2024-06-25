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
import java.util.*;

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
    @FXML
    private Label queueSizeLabel;
    @FXML
    private ListView<String> historyListView;

    private PatronDaoImpl patronDao;
    private BookDaoImpl bookDao;
    private TransactionDaoImpl transactionDao;
    private ObservableList<Transaction> transactionList;

    private Queue<Transaction> transactionQueue;
    private Stack<Transaction> transactionHistory;

    public void initialize() {
        patronDao = new PatronDaoImpl();
        bookDao = new BookDaoImpl();
        transactionDao = new TransactionDaoImpl();
        transactionList = FXCollections.observableArrayList();
        transactionQueue = new LinkedList<>();
        transactionHistory = new Stack<>();
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
        updateQueueSizeLabel();
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
                showAlert(Alert.AlertType.WARNING,"Patron Not Selected", "Please select a patron.");
                return;
            }

            if (selectedBook == null) {
                showAlert(Alert.AlertType.WARNING,"Book Not Selected", "Please select a book.");
                return;
            }

            LocalDate transactionDate = transactionDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            LocalDate dueDate = dueDatePicker.getValue();

            if (transactionDate == null || returnDate == null || dueDate == null) {
                showAlert(Alert.AlertType.ERROR,"Invalid Input", "Please enter valid dates.");
                return;
            }

            Transaction transaction = new Transaction(selectedPatron, selectedBook, transactionDate, returnDate, dueDate);
            transactionQueue.offer(transaction); // Add transaction to queue
            transactionList.add(transaction); // Add transaction to table view
            updateQueueSizeLabel(); // Update queue size label
            clearInputFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR,"Invalid Input", "Please enter valid numeric values for Patron ID and Book ID.");
        }
    }

    @FXML
    private void processTransactions() {
        while (!transactionQueue.isEmpty()) {
            Transaction transaction = transactionQueue.poll();
            transaction.setReturnDate(LocalDate.now()); // Simulating return today
            transactionDao.addTransaction(transaction); // Persist transaction
            transactionHistory.push(transaction); // Add to history stack
            transactionList.remove(transaction); // Remove from table view
        }
        updateQueueSizeLabel(); // Update queue size label
    }

    @FXML
    private void undoLastTransaction() {
        if (!transactionHistory.isEmpty()) {
            Transaction lastTransaction = transactionHistory.pop();
            // Implement logic to undo transaction (if needed)
            // Example: transactionDao.deleteTransaction(lastTransaction.getTransactionUd());
            transactionList.add(lastTransaction); // Add back to table view
            updateQueueSizeLabel(); // Update queue size label
        }
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
            showAlert(Alert.AlertType.WARNING, "No Transaction Selected", "Please select a transaction to delete.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
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
        private void updateQueueSizeLabel() {
            int queueSize = transactionQueue.size();
            queueSizeLabel.setText("Queue Size: " + queueSize);
        }


        @FXML
        private void viewTransactionHistory() {
            List<String> historyItems = new ArrayList<>();
            for (Transaction transaction : transactionHistory) {
                historyItems.add(transaction.toString()); // Assuming you have a proper toString method in Transaction class
            }
            historyListView.setItems(FXCollections.observableArrayList(historyItems));
        }
    }

