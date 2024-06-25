package org.example.librarymanagementsystemlab.test;

import org.example.librarymanagementsystemlab.daos.BookDao;
import org.example.librarymanagementsystemlab.daos.ReservationService;
import org.example.librarymanagementsystemlab.daos.TransactionDao;
import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
import org.example.librarymanagementsystemlab.daos.implementation.ReservationServiceImpl;
import org.example.librarymanagementsystemlab.daos.implementation.TransactionDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.models.Reservation;
import org.example.librarymanagementsystemlab.models.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationServiceManualTest {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ReservationService reservationService = new ReservationServiceImpl();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Reservation Service Menu ===");
            System.out.println("1. Place Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. List All Reservations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    placeReservations();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    listAllReservations();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }

        scanner.close();
    }

    private static void placeReservations() {
        System.out.println("\n=== Place Reservation ===");
        System.out.print("Enter Patron's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Patron's last name: ");
        String lastName = scanner.nextLine();
        Patron patron = new Patron(firstName, lastName,LocalDate.now());

        System.out.print("Enter Book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Book isbn: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Book category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Book quantity: ");
        int quantity = scanner.nextInt();
        Book book = new Book(isbn,title, author,LocalDate.now(),category,quantity); // Assuming Book constructor includes title and author

        reservationService.placeReservation (patron, book);
    }

    private static void cancelReservation() {
        System.out.println("\n=== Cancel Reservation ===");
        System.out.print("Enter Reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        reservationService.cancelReservation(reservationId);
    }

    private static void listAllReservations() {
        System.out.println("\n=== List All Reservations ===");
        List<Reservation> reservations = reservationService.listAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}
