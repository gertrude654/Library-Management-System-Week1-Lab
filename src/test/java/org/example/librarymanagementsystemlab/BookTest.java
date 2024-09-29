//package org.example.librarymanagementsystemlab;
//
////
////
////import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
////import org.example.librarymanagementsystemlab.models.Book;
////import org.example.librarymanagementsystemlab.tables.DatabaseConnection;
////import org.junit.Before;
////import org.junit.Test;
////import org.junit.jupiter.api.extension.ExtendWith;
////import org.mockito.Mockito;
////import org.mockito.junit.jupiter.MockitoExtension;
////
////import java.sql.Connection;
////import java.sql.Date;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.time.LocalDate;
////import java.util.List;
////
////import static org.junit.Assert.*;
////import static org.mockito.Mockito.*;
////
////@ExtendWith(MockitoExtension.class)
////public class BookTest {
////    private BookDaoImpl bookDao;
////    private Connection connection;
////    private PreparedStatement preparedStatement;
////    private ResultSet resultSet;
////
////    @Before
////    public void setUp() throws SQLException {
////        bookDao = new BookDaoImpl();
////        connection = mock(Connection.class);
////        preparedStatement = mock(PreparedStatement.class);
////        resultSet = mock(ResultSet.class);
////
////        // Mock the DatabaseConnection to return the mock connection
////        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
////        when(databaseConnection.getConnection()).thenReturn(connection);
////    }
////
////    @Test
////    public void testAddBook() throws SQLException {
////        Book book = new Book("1234567890", "JUnit Testing", "John Doe", LocalDate.now(), "Testing", 5);
////
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(preparedStatement.executeUpdate()).thenReturn(1);
////
////        Book result = bookDao.addBook(book);
////
////        assertEquals(book, result);
////        verify(preparedStatement, times(1)).executeUpdate();
////    }
////
////    @Test
////    public void testUpdateBook() throws SQLException {
////        Book book = new Book(1, "1234567890", "JUnit Testing", "John Doe", LocalDate.now(), "Testing", 5);
////
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(preparedStatement.executeUpdate()).thenReturn(1);
////
////        bookDao.updateBook(book);
////
////        verify(preparedStatement, times(1)).executeUpdate();
////    }
////
////    @Test
////    public void testDeleteBook() throws SQLException {
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(preparedStatement.executeUpdate()).thenReturn(1);
////
////        bookDao.deleteBook(1);
////
////        verify(preparedStatement, times(1)).executeUpdate();
////    }
////
////    @Test
////    public void testGetBookById() throws SQLException {
////        Book book = new Book(1, "1234567890", "JUnit Testing", "John Doe", LocalDate.now(), "Testing", 5);
////
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(preparedStatement.executeQuery()).thenReturn(resultSet);
////        when(resultSet.next()).thenReturn(true);
////        when(resultSet.getInt("book_id")).thenReturn(book.getBook_id());
////        when(resultSet.getString("isbn")).thenReturn(book.getIsbn());
////        when(resultSet.getString("title")).thenReturn(book.getTitle());
////        when(resultSet.getString("author")).thenReturn(book.getAuthor());
////        when(resultSet.getDate("publication_date")).thenReturn(Date.valueOf(book.getPublication_date()));
////        when(resultSet.getString("category")).thenReturn(book.getCategory());
////        when(resultSet.getInt("quantity")).thenReturn(book.getQuantity());
////
////        Book result = bookDao.getBookById(1);
////
////        assertEquals(book, result);
////        verify(preparedStatement, times(1)).executeQuery();
////    }
////
////    @Test
////    public void testListAllBooks() throws SQLException {
////        Book book = new Book(1, "1234567890", "JUnit Testing", "John Doe", LocalDate.now(), "Testing", 5);
////        List<Book> books = List.of(book);
////
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(preparedStatement.executeQuery()).thenReturn(resultSet);
////        when(resultSet.next()).thenReturn(true, false);
////        when(resultSet.getInt("book_id")).thenReturn(book.getBook_id());
////        when(resultSet.getString("isbn")).thenReturn(book.getIsbn());
////        when(resultSet.getString("title")).thenReturn(book.getTitle());
////        when(resultSet.getString("author")).thenReturn(book.getAuthor());
////        when(resultSet.getDate("publication_date")).thenReturn(Date.valueOf(book.getPublication_date()));
////        when(resultSet.getString("category")).thenReturn(book.getCategory());
////        when(resultSet.getInt("quantity")).thenReturn(book.getQuantity());
////
////        List<Book> result = bookDao.listAllBooks();
////
////        assertEquals(books, result);
////        verify(preparedStatement, times(1)).executeQuery();
////    }
////}
////
//
////
////import org.example.librarymanagementsystemlab.daos.BookDao;
////import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
////import org.example.librarymanagementsystemlab.models.Book;
////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.api.extension.ExtendWith;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.junit.jupiter.MockitoExtension;
////
////import java.time.LocalDate;
////
////import static org.junit.jupiter.api.Assertions.assertNotNull;
////import static org.mockito.ArgumentMatchers.any;
////import static org.mockito.Mockito.when;
////
//
////public class BookTest {
////
////    @Mock
////    BookDao bookDao;
////
////    @InjectMocks
////    BookDaoImpl bookDaoImpl;
////
////    //BookDao bookService = new BookDaoImpl();
////
////    @Test
////    public void testSave(){
////
////        Book book = new Book();
////        book.setBook_id(1);
////        book.setIsbn("123");
////        book.setTitle("Book Title");
////        book.setAuthor("Author");
////        book.setPublication_date(LocalDate.now());
////        book.setCategory("Category");
////        book.setQuantity(10);
////
////        when(bookDao.addBook(any(Book.class))).thenReturn(book);
////
////
////        Book saved = bookDaoImpl.addBook(book);
////
////        assertNotNull(saved);
////
////
////
////
////
////
////
////
////    }
////
////}
//
//
//
//
//
//import org.example.librarymanagementsystemlab.daos.implementation.BookDaoImpl;
//import org.example.librarymanagementsystemlab.models.Book;
//import org.example.librarymanagementsystemlab.tables.DatabaseConnection;
//import org.junit.jupiter.api.*;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
////import org.powermock.modules.junit4.PowerMockExtension;
//import org.powermock.modules.junit.jupiter.PowerMockExtension;
//
//
//
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.LinkedList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(PowerMockExtension.class)
//@PrepareForTest(DatabaseConnection.class)
//@PowerMockIgnore("javax.management.*")
//public class BookTest {
//
//    @Mock
//    public DatabaseConnection mockDbConnection;
//
//    @Mock
//    public Connection mockConnection;
//
//    @Mock
//    public PreparedStatement mockPreparedStatement;
//
//    @Mock
//    public ResultSet mockResultSet;
//
//    @InjectMocks
//    public BookDaoImpl bookDao;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddBook_Success() throws Exception {
//        Book book = new Book("1234567890", "JUnit in Action", "John Doe", LocalDate.now(), "Technology", 5);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        Book result = bookDao.addBook(book);
//        assertEquals(book, result);
//        verify(mockPreparedStatement, times(1)).setString(1, book.getIsbn());
//        verify(mockPreparedStatement, times(1)).setString(2, book.getTitle());
//        verify(mockPreparedStatement, times(1)).setString(3, book.getAuthor());
//        verify(mockPreparedStatement, times(1)).setDate(4, Date.valueOf(book.getPublication_date()));
//        verify(mockPreparedStatement, times(1)).setString(5, book.getCategory());
//        verify(mockPreparedStatement, times(1)).setInt(6, book.getQuantity());
//    }
//
//    @Test
//    void testAddBook_Failure() throws Exception {
//        Book book = new Book("1234567890", "JUnit in Action", "John Doe", LocalDate.now(), "Technology", 5);
//      //  when(mockDbConnection.getConnection()).thenReturn(mockConnection);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeUpdate()).thenReturn(0);
//
//        Book result = bookDao.addBook(book);
//        assertEquals(book, result);
//    }
//
//    @Test
//    void testGetBookById() throws Exception {
//        int bookId = 1;
//       // when(mockDbConnection.getConnection()).thenReturn(mockConnection);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getInt("book_id")).thenReturn(bookId);
//        when(mockResultSet.getString("isbn")).thenReturn("1234567890");
//        when(mockResultSet.getString("title")).thenReturn("JUnit in Action");
//        when(mockResultSet.getString("author")).thenReturn("John Doe");
//        when(mockResultSet.getDate("publication_date")).thenReturn(Date.valueOf(LocalDate.now()));
//        when(mockResultSet.getString("category")).thenReturn("Technology");
//        when(mockResultSet.getInt("quantity")).thenReturn(5);
//
//        Book result = bookDao.getBookById(bookId);
//        assertNotNull(result);
//        assertEquals(bookId, result.getBook_id());
//    }
//
////    @ParameterizedTest
////    @CsvSource({
////            "1234567890, JUnit in Action, John Doe, 2024-07-20, Technology, 5",
////            "0987654321, Mockito Made Simple, Jane Smith, 2023-06-15, Education, 3"
////    })
////    void testAddBook_Parameterized(String isbn, String title, String author, String pubDate, String category, int quantity) throws Exception {
////        Book book = new Book(isbn, title, author, LocalDate.parse(pubDate), category, quantity);
////        when(mockDbConnection.getConnection()).thenReturn(mockConnection);
////        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
////        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
////
////        Book result = bookDao.addBook(book);
////        assertEquals(book, result);
////    }
//
//    @Test
//    void testUpdateBook_ExceptionHandling() throws Exception {
//        Book book = new Book("1234567890", "JUnit in Action", "John Doe", LocalDate.now(), "Technology", 5);
//        book.setBook_id(1);
//        when(mockDbConnection.getConnection()).thenThrow(new SQLException("Database connection error"));
//
//        assertThrows(RuntimeException.class, () -> bookDao.updateBook(book));
//    }
//
//    @Test
//    void testDeleteBook() throws Exception {
//        int bookId = 1;
//       // when(mockDbConnection.getConnection()).thenReturn(mockConnection);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
//
//        assertDoesNotThrow(() -> bookDao.deleteBook(bookId));
//        verify(mockPreparedStatement, times(1)).setInt(1, bookId);
//    }
//
//    @Test
//    void testListAllBooks() throws Exception {
//     //   when(mockDbConnection.getConnection()).thenReturn(mockConnection);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//
//        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
//        when(mockResultSet.getInt("book_id")).thenReturn(1).thenReturn(2);
//        when(mockResultSet.getString("isbn")).thenReturn("1234567890").thenReturn("0987654321");
//        when(mockResultSet.getString("title")).thenReturn("JUnit in Action").thenReturn("Mockito Made Simple");
//        when(mockResultSet.getString("author")).thenReturn("John Doe").thenReturn("Jane Smith");
//        when(mockResultSet.getDate("publication_date")).thenReturn(Date.valueOf(LocalDate.now())).thenReturn(Date.valueOf(LocalDate.now().minusDays(1)));
//        when(mockResultSet.getString("category")).thenReturn("Technology").thenReturn("Education");
//        when(mockResultSet.getInt("quantity")).thenReturn(5).thenReturn(3);
//
//        List<Book> books = bookDao.listAllBooks();
//        assertNotNull(books);
//        assertEquals(2, books.size());
//    }
//
//    @AfterEach
//    void tearDown() {
//        verifyNoMoreInteractions(mockDbConnection, mockConnection, mockPreparedStatement, mockResultSet);
//    }
//}
