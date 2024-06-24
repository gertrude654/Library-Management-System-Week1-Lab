


package org.example.librarymanagementsystemlab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private void openBooksPage() {
        loadPage("/org/example/librarymanagementsystemlab/book-view.fxml");
    }
    @FXML
    private void openPatronsPage() {
        loadPage("/org/example/librarymanagementsystemlab/patron-view.fxml");
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
