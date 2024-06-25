package org.example.librarymanagementsystemlab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeController {

    private Map<String, Stage> openStages = new HashMap<>();

    @FXML
    private void openBooksPage() {
        openPage("/org/example/librarymanagementsystemlab/book-view.fxml");
    }

    @FXML
    private void openPatronsPage() {
        openPage("/org/example/librarymanagementsystemlab/patron-view.fxml");
    }

    @FXML
    private void openTransactionsPage() {
        openPage("/org/example/librarymanagementsystemlab/transaction-view.fxml");
    }

    private void openPage(String fxmlPath) {
        try {
            Stage currentStage = openStages.get(fxmlPath);
            if (currentStage != null && currentStage.isShowing()) {
                currentStage.toFront(); // Bring existing stage to front
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Library Management System"); // Set a title if needed
            newStage.show();

            // Store the stage in the map
            openStages.put(fxmlPath, newStage);

            // Close previous stage if it exists
            if (currentStage != null) {
                currentStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
