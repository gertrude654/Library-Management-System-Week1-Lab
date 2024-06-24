package org.example.librarymanagementsystemlab.models;


import java.time.LocalDate;
import java.util.Stack;


public class Transaction {

    private int transactionUd;
    private Patron patronId;
    private Book bookId;
    private LocalDate transactionDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Transaction() {
    }

    public Transaction(int transactionUd, Patron patronId, Book bookId, LocalDate transactionDate, LocalDate dueDate, LocalDate returnDate) {
        this.transactionUd = transactionUd;
        this.patronId = patronId;
        this.bookId = bookId;
        this.transactionDate = transactionDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Transaction(Patron patronId, Book bookId, LocalDate transactionDate, LocalDate dueDate, LocalDate returnDate) {
        this.patronId = patronId;
        this.bookId = bookId;
        this.transactionDate = transactionDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;

    }

    public Transaction(Stack<Patron> patron, Book book, LocalDate transactionDate, LocalDate returnDate, LocalDate dueDate) {
    }

    public Patron getPatronId() {
        return patronId;
    }

    public void setPatronId(Patron patronId) {
        this.patronId = patronId;
    }

    public Book getItemId() {
        return bookId;
    }

    public void setItemId(Book itemId) {
        this.bookId = bookId;
    }

    public int getTransactionUd() {
        return transactionUd;
    }

    public void setTransactionUd(int transactionUd) {
        this.transactionUd = transactionUd;
    }


    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Helper methods to get the IDs
    public int getPatronIdInt() {
        return patronId.getPatron_id();
    }

    public int getBookIdInt() {
        return bookId.getBook_id();
    }
}
