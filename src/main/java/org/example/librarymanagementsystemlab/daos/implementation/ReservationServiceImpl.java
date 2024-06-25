package org.example.librarymanagementsystemlab.daos.implementation;

import org.example.librarymanagementsystemlab.daos.BookDao;
import org.example.librarymanagementsystemlab.daos.ReservationService;
import org.example.librarymanagementsystemlab.daos.TransactionDao;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.models.Reservation;
import org.example.librarymanagementsystemlab.models.Transaction;

import java.time.LocalDate;
import java.util.*;

public class ReservationServiceImpl implements ReservationService {
    private Queue<Reservation> reservationQueue = new LinkedList<>();
    private static int nextReservationId = 1;

    private TransactionDao transactionDao = new TransactionDaoImpl(); // Assuming transactionDao is initialized correctly

    @Override
    public void placeReservation(Patron patron, Book book) {
        // Check if book is available
        if (isBookAvailable(book.getBook_id())) {
            // Create a transaction and add it directly
            Transaction transaction = new Transaction();
            transaction.setPatronId(patron); // Assuming Transaction model has setPatronId method
            transaction.setBookId(book);     // Assuming Transaction model has setBookId method
            transaction.setTransactionDate(LocalDate.now()); // Assuming Transaction model has setTransactionDate method

            transactionDao.addTransaction(transaction);
            System.out.println("Book is available");
        } else {
            // Otherwise, add to reservation queue/list
            Reservation reservation = new Reservation(nextReservationId++, patron, book, LocalDate.now());
            reservationQueue.offer(reservation);
            System.out.println("Book reserved successfully. You will be notified when it's available.");
        }
    }

    @Override
    public void cancelReservation(int reservationId) {
        // Find and remove reservation from the queue
        Reservation reservationToRemove = reservationQueue.stream()
                .filter(reservation -> reservation.getReservationId() == reservationId)
                .findFirst()
                .orElse(null);

        if (reservationToRemove != null) {
            reservationQueue.remove(reservationToRemove);
            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("Failed to cancel reservation. Reservation not found.");
        }
    }

    @Override
    public List<Reservation> listAllReservations() {
        return new ArrayList<>(reservationQueue);
    }

    private boolean isBookAvailable(int bookId) {
        // Implement logic to check book availability
        BookDao bookDao = new BookDaoImpl();  // Assuming BookDaoImpl implements BookDao correctly
        Book book = bookDao.getBookById(bookId);
        return book != null && book.getQuantity() > 0;
    }
}
