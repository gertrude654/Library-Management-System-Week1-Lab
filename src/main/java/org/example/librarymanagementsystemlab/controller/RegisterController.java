package org.example.librarymanagementsystemlab.controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.librarymanagementsystemlab.daos.UserDao;
import org.example.librarymanagementsystemlab.daos.implementation.UserDaoImpl;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserDao userDao = new UserDaoImpl();
    private MainController mainController; // Reference to the main controller


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Please enter both username and password.");
            return;
        }

        if (userDao.registerUser(username, password)) {
            showAlert("Registration successful!");
            loadPage("/org/example/librarymanagementsystemlab/home-view.fxml");
        } else {
            showAlert("Registration failed. Please try again.");
        }
    }

    @FXML
    private void switchToLogin() {
            loadPage("/org/example/librarymanagementsystemlab/login-view.fxml");

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
