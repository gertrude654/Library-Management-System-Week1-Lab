package org.example.librarymanagementsystemlab.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
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
            patronDao.deletePatron(selectedPatron.getPatron_id());
            refreshTable();
        }
    }

    @FXML
    private void searchPatron() {
        // Implementation for searching patrons (e.g., by name)
        // This would involve a query to the database and updating the TableView
        // For simplicity, we'll leave this method empty or use a filter on the patronList
    }

    private void refreshTable() {
        patronList.clear();
        patronList.addAll(patronDao.listAllPatrons());
    }
}

