package org.example.librarymanagementsystemlab.daos.implementation;

import org.example.librarymanagementsystemlab.daos.BookDao;
import org.example.librarymanagementsystemlab.models.Book;
import org.example.librarymanagementsystemlab.tables.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO book(isbn,title, author, publication_date, category,quantity) VALUES (?,?,?, ?, ?, ?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setDate(4, Date.valueOf(book.getPublication_date()));
            ps.setString(5, book.getCategory());
            ps.setInt(6, book.getQuantity());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected != 0){
                System.out.println("Library book added successfully.");

            } else {
                System.out.println("Library book not added");
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE  book SET  isbn = ?,title = ?, author = ?, publication_date = ?, category = ? ,quantity = ? WHERE book_id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setDate(4, Date.valueOf(book.getPublication_date()));
            ps.setString(5, book.getCategory());
            ps.setInt(6, book.getQuantity());
            ps.setInt(7,book.getBook_id());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected != 0){
                System.out.println("Library book updated successfully.");

            } else {
                System.out.println("Library book not updated");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM book WHERE book_id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected != 0){
                System.out.println("Library book deleted successfully.");

            } else {
                System.out.println("Library book not updated");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Book getBookById(int id) {
        Book b = new Book();
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM book WHERE book_id =?";
            PreparedStatement bs = con.prepareStatement(sql);
            bs.setInt(1, b.getBook_id());
            ResultSet rs = bs.executeQuery();
            if (rs.next()) {
                b.setIsbn(rs.getString("isbn"));
                b.setTitle(rs.getString("name"));
                b.setAuthor(rs.getString("author"));
                b.setPublication_date(rs.getDate("publication_date").toLocalDate());
                b.setQuantity(rs.getInt("quantity"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Book> listAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("publication_date").toLocalDate(),
                        rs.getString("category"),
                        rs.getInt("quantity")
                );
                books.add(book);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;

    }
}
