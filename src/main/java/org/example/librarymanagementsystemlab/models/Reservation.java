package org.example.librarymanagementsystemlab.models;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private Patron patronId;
    private Book bookId;
    private LocalDate reservationDate;

    public Reservation(int reservationId, Patron patronId, Book bookId, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.patronId = patronId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
    }

    // Getters and setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Patron getPatronId() {
        return patronId;
    }

    public void setPatronId(Patron patronId) {
        this.patronId = patronId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    // Override toString() method for debugging or logging purposes
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", patronId=" + patronId +
                ", bookId=" + bookId +
                ", reservationDate=" + reservationDate +
                '}';
    }
}

