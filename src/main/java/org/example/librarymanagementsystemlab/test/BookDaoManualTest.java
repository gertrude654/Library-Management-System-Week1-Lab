package org.example.librarymanagementsystemlab.test;

import org.example.librarymanagementsystemlab.daos.BookDao;
import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
import org.example.librarymanagementsystemlab.models.Book;

import java.time.LocalDate;
import java.util.List;

public class BookDaoManualTest {

    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();

        // Test addBook method
        System.out.println("Adding a book...");
        Book bookToAdd = new Book("123456", "Test Book", "Test Author", LocalDate.now(), "Test Category", 1);
        bookDao.addBook(bookToAdd);

        // Test updateBook method
        System.out.println("\nUpdating a book...");
        Book bookToUpdate = new Book("1234567890", "Updated Book", "Updated Author", LocalDate.now(), "Updated Category", 2);
        bookToUpdate.setBook_id(1); 
        bookDao.updateBook(bookToUpdate);

        // Test deleteBook method
        System.out.println("\nDeleting a book...");
        int bookIdToDelete = 1;
        bookDao.deleteBook(bookIdToDelete);

        // Test getBookById method
        System.out.println("\nGetting a book by ID...");
        int bookIdToRetrieve = 2; // Assuming book with id 2 exists
        Book retrievedBook = bookDao.getBookById(bookIdToRetrieve);
        if (retrievedBook != null) {
            System.out.println("Retrieved Book: " + retrievedBook);
        } else {
            System.out.println("Book with ID " + bookIdToRetrieve + " not found.");
        }

        // Test listAllBooks method
        System.out.println("\nListing all books...");
        List<Book> allBooks = bookDao.listAllBooks();
        for (Book book : allBooks) {
            System.out.println(book);
        }

    }
}
