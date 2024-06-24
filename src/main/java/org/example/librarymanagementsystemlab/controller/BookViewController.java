package org.example.librarymanagementsystemlab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.ButtonType;



import java.time.LocalDate;

public class BookViewController {

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, Integer> bookIdColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, LocalDate> publicationDateColumn;
    @FXML
    private TableColumn<Book, String> categoryColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;

    @FXML
    private TextField isbnField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private DatePicker publicationDatePicker;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField quantityField;


    private BookDaoImpl bookDao;
    private ObservableList<Book> bookList;

    public void initialize() {
        bookDao = new BookDaoImpl();
        bookList = FXCollections.observableArrayList();
        tableView.setItems(bookList);

        // Initialize TableView columns
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("book_id"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publicationDateColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("publication_date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("quantity"));

        refreshTable();
    }

    @FXML
    private void addBook() {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        LocalDate publicationDate = publicationDatePicker.getValue();
        String category = categoryField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        Book book = new Book(isbn, title, author, publicationDate, category, quantity);
        bookDao.addBook(book);
        refreshTable();
    }

    @FXML
    private void updateBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setIsbn(isbnField.getText());
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setPublication_date(publicationDatePicker.getValue());
            selectedBook.setCategory(categoryField.getText());
            selectedBook.setQuantity(Integer.parseInt(quantityField.getText()));
            bookDao.updateBook(selectedBook);
            refreshTable();
        }
    }

    @FXML
    private void deleteBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Book");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the book with ID " + selectedBook.getBook_id() + "?");

            // Option to confirm or cancel deletion
            ButtonType buttonTypeDelete = new ButtonType("Delete");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

            // Show the alert and wait for user response
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeDelete) {
                    // User clicked Delete button
                    bookDao.deleteBook(selectedBook.getBook_id());
                    refreshTable();
                } else {
                    // User clicked Cancel button or closed the dialog
                    // Do nothing
                }
            });
        } else {
            // No book selected in tableView
            showAlert(AlertType.WARNING, "No Book Selected", "Please select a book to delete.");
        }
    }


    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void refreshTable() {
        bookList.clear();
        bookList.addAll(bookDao.listAllBooks());
    }


}
