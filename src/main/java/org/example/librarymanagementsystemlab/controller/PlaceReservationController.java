package org.example.librarymanagementsystemlab.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.librarymanagementsystemlab.daos.BookDao;
import org.example.librarymanagementsystemlab.daos.PatronDao;
import org.example.librarymanagementsystemlab.daos.ReservationService;
import org.example.librarymanagementsystemlab.daos.TransactionDao;
import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.ReservationServiceImpl;
import org.example.librarymanagementsystemlab.daos.implementation.TransactionDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;

import java.util.List;
import java.util.Stack;

public class PlaceReservationController {

    @FXML
    private ComboBox<Book> bookComboBox;

    @FXML
    private TextField patronIdField;

    private PatronDao patronDao = new PatronDaoImpl(); // Implement as needed
    private BookDao bookDao = new BookDaoImpl(); // Implement as needed
    private ReservationService reservationService = new ReservationServiceImpl(); // Implement as needed

    @FXML
    private void initialize() {
        // Populate bookComboBox with available books
        List<Book> books = bookDao.listAllBooks(); // Implement as needed
        bookComboBox.getItems().addAll(books);
    }

    @FXML
    private void handlePlaceReservation(ActionEvent event) {
        try {
            int patronId = Integer.parseInt(patronIdField.getText().trim());
            Patron patron = patronDao.getPatronById(patronId); // Implement as needed

            Book selectedBook = bookComboBox.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                showAlert("Please select a book.");
                return;
            }

            // Place reservation
            reservationService.placeReservation(patron, selectedBook);
            showAlert("Reservation placed successfully.");

        } catch (NumberFormatException e) {
            showAlert("Invalid patron ID format.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
