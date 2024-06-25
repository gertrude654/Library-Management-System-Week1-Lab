package org.example.librarymanagementsystemlab.daos;

import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.models.Reservation;

import java.util.List;
import java.util.Stack;

public interface ReservationService {
    void placeReservation(Patron patron, Book book);
    void cancelReservation(int reservationId);
    List<Reservation> listAllReservations();

}
