//package org.example.librarymanagementsystemlab.controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import org.example.librarymanagementsystemlab.daos.UserDao;
//import org.example.librarymanagementsystemlab.daos.implementation.UserDaoImpl;
//
//import java.io.IOException;
//
//public class LoginController {
//
//    @FXML
//    private TextField usernameField;
//
//    @FXML
//    private PasswordField passwordField;
//
//    private UserDao userDao = new UserDaoImpl();
//
//
//    @FXML
//    private void handleLogin() {
//        String username = usernameField.getText().trim();
//        String password = passwordField.getText().trim();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            showAlert("Please enter both username and password.");
//            return;
//        }
//
//        if (userDao.validateUser(username, password)) {
//            // Login successful
//           // mainController.showHomePage();
//            loadPage("/org/example/librarymanagementsystemlab/home-view.fxml");
//
//        } else {
//            showAlert("Invalid username or password.");
//        }
//    }
//
//    @FXML
//    void switchToRegister(ActionEvent event) {
//            loadPage("/org/example/librarymanagementsystemlab/register-view.fxml");
//
//    }
//    private void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Login Information");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    private void loadPage(String fxmlPath) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

package org.example.librarymanagementsystemlab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.librarymanagementsystemlab.daos.UserDao;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.UserDaoImpl;
import org.example.librarymanagementsystemlab.models.Patron;


import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Text loginStatus;

    private PatronDaoImpl patronDao;


    // Hardcoded admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @FXML
    public void initialize() {
        patronDao = new PatronDaoImpl();
    }
    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if the user is admin
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
           // loginStatus.setText("Login successful: Admin");
            // Redirect to admin dashboard or load admin panel here.
            loadPage("/org/example/librarymanagementsystemlab/home-view.fxml");

        } else {
            // Validate Patron login
            Patron patron = patronDao.validatePatron(username, password);
            if (patron != null) {
               // loginStatus.setText("Login successful: Patron");
                // Redirect to patron dashboard or load patron panel here.
                loadPage("/org/example/librarymanagementsystemlab/patron-view-dashboard.fxml");

            } else {
                loginStatus.setText("Invalid username or password");
            }
        }
    }
    @FXML
    void switchToRegister(ActionEvent event) {
        loadPage("/org/example/librarymanagementsystemlab/register-view.fxml");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Information");
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

    // Load home page for Patron (Normal User)
    private void loadPatronHomePage(Patron patron) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/librarymanagementsystemlab/patron-home-view.fxml"));
            Parent root = loader.load();

            // Pass patron data to the next controller
            PatronHomeController controller = loader.getController();
            controller.setPatron(patron);  // Assuming PatronHomeController has this method

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
