//package org.example.librarymanagementsystemlab.controller;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//public class HomeController  implements Initializable {
//
//
//    private Stage primaryStage;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        // Initialize anything if needed
//    }
//
//    public void setPrimaryStage(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//    }
//
//    @FXML
//    private void openBooksPage() {
//        // Load and show BooksView.fxml
//        // Example code to open a new stage for BooksView
//        Stage booksStage = new Stage();
//        // Assuming BooksView.fxml is in the same package as HomeController
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Book-View.fxml"));
//        Parent root;
//        try {
//            root = loader.load();
//            booksStage.setTitle("Books");
//            booksStage.setScene(new Scene(root));
//            booksStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert("Error", "Failed to open Books page.");
//        }
//    }
//
//    @FXML
//    private void openPatronsPage() {
//        // Load and show PatronView.fxml
//        // Example code to open a new stage for PatronView
//        Stage patronsStage = new Stage();
//        // Assuming PatronView.fxml is in the same package as HomeController
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("patron-view.fxml"));
//        Parent root;
//        try {
//            root = loader.load();
//            patronsStage.setTitle("Patrons");
//            patronsStage.setScene(new Scene(root));
//            patronsStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert("Error", "Failed to open Patrons page.");
//        }
//    }
//
//    @FXML
//    private void openTransactionsPage() {
//        // Load and show TransactionView.fxml
//        // Example code to open a new stage for TransactionView
//        Stage transactionsStage = new Stage();
//        // Assuming TransactionView.fxml is in the same package as HomeController
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("transaction-view.fxml"));
//        Parent root;
//        try {
//            root = loader.load();
//            transactionsStage.setTitle("Transactions");
//            transactionsStage.setScene(new Scene(root));
//            transactionsStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert("Error", "Failed to open Transactions page.");
//        }
//    }
//
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}
//


package org.example.librarymanagementsystemlab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private void openPatronsPage() {
        loadPage("/org/example/librarymanagementsystemlab/patron-view.fxml");
    }
    @FXML
    private void openBooksPage() {
        loadPage("org/example/librarymanagementsystemlab/book-view.fxml");
    }

    @FXML
    private void openTransactionsPage() {
        loadPage("/org/example/librarymanagementsystemlab/transaction-view.fxml");
    }

    private void loadPage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
