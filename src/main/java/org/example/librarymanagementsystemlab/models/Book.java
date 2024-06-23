package org.example.librarymanagementsystemlab.models;

import java.time.LocalDate;

public class Book{

    private int book_id;
    private String isbn;
    private String title;
    private String author;
    private LocalDate publication_date;
    private String category;
    private int quantity;

    public Book() {
    }

    public Book(int book_id, String isbn, String title, String author, LocalDate publication_date, String category, int quantity) {
        this.book_id = book_id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publication_date = publication_date;
        this.category = category;
        this.quantity = quantity;
    }

    public Book(String isbn, String title, String author, LocalDate publication_date, String category, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publication_date = publication_date;
        this.category = category;
        this.quantity = quantity;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(LocalDate publication_date) {
        this.publication_date = publication_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
