//package org.example.librarymanagementsystemlab.controller;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
//import org.example.librarymanagementsystemlab.models.Book;
//
//import java.util.List;
//
//public class PatronView extends Application {
//
//    private BookDaoImpl bookDao = new BookDaoImpl();
//    private TableView<Book> tableView = new TableView<>();
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Patron - Available Books");
//
//        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
//        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
//
//        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
//        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
//
//        TableColumn<Book, String> publicationDateCol = new TableColumn<>("Publication Date");
//        publicationDateCol.setCellValueFactory(new PropertyValueFactory<>("publication_date"));
//
//        TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
//        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
//
//        TableColumn<Book, Boolean> availableCol = new TableColumn<>("Available");
//        availableCol.setCellValueFactory(new PropertyValueFactory<>("is_available"));
//
//        tableView.getColumns().addAll(titleCol, authorCol, publicationDateCol, categoryCol, availableCol);
//        loadBooks();
//
//        Button borrowButton = new Button("Borrow Selected Book");
//        borrowButton.setOnAction(e -> borrowBook());
//
//        Button returnButton = new Button("Return Selected Book");
//        returnButton.setOnAction(e -> returnBook());
//
//        VBox vbox = new VBox(tableView, borrowButton, returnButton);
//        Scene scene = new Scene(vbox);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void loadBooks() {
//        List<Book> books = bookDao.listAllBooks();
//        tableView.getItems().clear();
//        tableView.getItems().addAll(books);
//    }
//
//    private void borrowBook() {
//        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
//        if (selectedBook != null && selectedBook.isIs_available()) {
//            selectedBook.setIs_available(false);
//            bookDao.updateBook(selectedBook);
//            loadBooks(); // Refresh the book list
//        }
//    }
//
//    private void returnBook() {
//        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
//        if (selectedBook != null && !selectedBook.isIs_available()) {
//            selectedBook.setIs_available(true);
//            bookDao.updateBook(selectedBook);
//            loadBooks(); // Refresh the book list
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}


package org.example.librarymanagementsystemlab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;

import java.util.List;

public class PatronView {

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> publicationDateCol;

    @FXML
    private TableColumn<Book, String> categoryCol;

    @FXML
    private TableColumn<Book, Boolean> availableCol;

    @FXML
    private Button borrowButton;

    @FXML
    private Button returnButton;

    private BookDaoImpl bookDao = new BookDaoImpl();

    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publicationDateCol.setCellValueFactory(new PropertyValueFactory<>("publication_date"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        availableCol.setCellValueFactory(new PropertyValueFactory<>("is_available"));

        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = bookDao.listAllBooks();
        tableView.getItems().clear();
        tableView.getItems().addAll(books);
    }

    @FXML
    private void borrowBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null && selectedBook.isIs_available()) {
            selectedBook.setIs_available(false);
            bookDao.updateBook(selectedBook);
            loadBooks(); // Refresh the book list
        }
    }

    @FXML
    private void returnBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null && !selectedBook.isIs_available()) {
            selectedBook.setIs_available(true);
            bookDao.updateBook(selectedBook);
            loadBooks(); // Refresh the book list
        }
    }
}
