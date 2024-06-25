package org.example.librarymanagementsystemlab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



//    @FXML
//    public void initialize() {
//        showLoginPage(); // Initial view on application startup
//
//    }

    @FXML
    public void showLoginPage() {
        try {
            // Assuming you have some code that loads FXML and its controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();
            RegisterController registerController = loader.getController();

// Assuming you have already instantiated MainController
            MainController mainController = new MainController();

// Set the mainController in RegisterController
            registerController.setMainController(mainController);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Page");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showRegisterPage() {
        try {

            // Assuming you have some code that loads FXML and its controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Parent root = loader.load();
            RegisterController registerController = loader.getController();

// Assuming you have already instantiated MainController
            MainController mainController = new MainController();

// Set the mainController in RegisterController
            registerController.setMainController(mainController);




            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Register Page");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home Page");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//
//package org.example.librarymanagementsystemlab.controller;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class MainController {
//
//    @FXML
//    public void showLoginPage(){
//        loadPage("/org/example/librarymanagementsystemlab/login-view.fxml");
//    }
//    @FXML
//    public void showRegisterPage() {
//        loadPage("/org/example/librarymanagementsystemlab/register-view.fxml");
//    }
//
//    @FXML
//    public void showHomePage()  {
//        loadPage("/org/example/librarymanagementsystemlab/home-view.fxml");
//    }
//
//
//    public void loadPage(String fxmlPath) {
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
//
//
//}
