package org.example.librarymanagementsystemlab.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;

import java.time.LocalDate;

public class PatronViewController {

    @FXML
    private TableView<Patron> tableView;
    @FXML
    private TableColumn<Patron, Integer> idColumn;
    @FXML
    private TableColumn<Patron, String> firstNameColumn;
    @FXML
    private TableColumn<Patron, String> lastNameColumn;
    @FXML
    private TableColumn<Patron, LocalDate> dobColumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker dobPicker;

    private PatronDaoImpl patronDao;
    private ObservableList<Patron> patronList;

    @FXML
    public void initialize() {
        patronDao = new PatronDaoImpl();
        patronList = FXCollections.observableArrayList();
        tableView.setItems(patronList);

        idColumn.setCellValueFactory(new PropertyValueFactory<Patron, Integer>("patron_id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Patron, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Patron, String>("lastName"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Patron, LocalDate>("DOB"));

        refreshTable();
    }

    @FXML
    private void addPatron() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        LocalDate dob = dobPicker.getValue();
        Patron patron = new Patron(0, firstName, lastName, dob); // Assuming 0 as a placeholder ID
        patronDao.addPatron(patron);
        refreshTable();
    }

    @FXML
    private void updatePatron() {
        Patron selectedPatron = tableView.getSelectionModel().getSelectedItem();
        if (selectedPatron != null) {
            selectedPatron.setFirstName(firstNameField.getText());
            selectedPatron.setLastName(lastNameField.getText());
            selectedPatron.setDOB(dobPicker.getValue());
            patronDao.updatePatron(selectedPatron);
            refreshTable();
        }
    }

    @FXML
    private void deletePatron() {
        Patron selectedPatron = tableView.getSelectionModel().getSelectedItem();

        if (selectedPatron != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Book");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the book with ID " + selectedPatron.getPatron_id() + "?");

            // Option to confirm or cancel deletion
            ButtonType buttonTypeDelete = new ButtonType("Delete");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

            // Show the alert and wait for user response
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeDelete) {
                    // User clicked Delete button
                    patronDao.deletePatron(selectedPatron.getPatron_id());
                    refreshTable();
                } else {
                    // User clicked Cancel button or closed the dialog
                    // Do nothing
                }
            });
        } else {
            // No book selected in tableView
            showAlert(Alert.AlertType.WARNING, "No Book Selected", "Please select a book to delete.");
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
        patronList.clear();
        patronList.addAll(patronDao.listAllPatrons());
    }
}

