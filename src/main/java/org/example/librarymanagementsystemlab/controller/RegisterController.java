package org.example.librarymanagementsystemlab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
import org.example.librarymanagementsystemlab.models.Patron;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterController {


    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text registrationStatus;

    private PatronDaoImpl patronDao;

    @FXML
    public void initialize() {
        patronDao = new PatronDaoImpl();
    }

//    @FXML
//    private void handleRegister() {
//        String username = usernameField.getText().trim();
//        String password = passwordField.getText().trim();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            showAlert("Please enter both username and password.");
//            return;
//        }
//
//        if (userDao.registerUser(username, password)) {
//            showAlert("Registration successful!");
//            loadPage("/org/example/librarymanagementsystemlab/home-view.fxml");
//        } else {
//            showAlert("Registration failed. Please try again.");
//        }
//    }

    @FXML
    public void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username is taken
        Patron existingPatron = patronDao.validatePatron(username, password);
        if (existingPatron != null) {
            registrationStatus.setText("Username already taken.");
            return;
        }

        Patron newPatron = new Patron(0,firstName, lastName, username, password);
        patronDao.addPatron(newPatron);
        registrationStatus.setText("Registration successful!");
        showAlert("Registration successful!");
        loadPage("/org/example/librarymanagementsystemlab/controller/PatronView");
    }
//    @FXML
//    private void addPatron() {
//        String firstName = firstNameField.getText();
//        String lastName = lastNameField.getText();
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//
//        Patron patron = new Patron(0, firstName, lastName, username, password);
//        patronDao.addPatron(patron);
//        //refreshTable();
//    }

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
