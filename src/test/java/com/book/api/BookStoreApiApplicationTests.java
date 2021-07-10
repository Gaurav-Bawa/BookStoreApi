package com.book.api;

import com.book.api.controller.BookController;
import com.book.api.entity.Book;
import com.book.api.entity.Promotion;
import com.book.api.model.Request;
import com.book.api.model.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookStoreApiApplicationTests {

    static List<Promotion> promotions = new ArrayList<>();
    static List<Book> books = new ArrayList<>();
    @Autowired
    private BookController controller;

    @BeforeAll
    static void init() {

        books.add(new Book(123456788787L, "C++", "C++ Tutorial", "Keithy Seira", "Educational", new BigDecimal(300)));
        books.add(new Book(123456788788L, "Harry Potter", "Harry Potter - Part 2", "J K Rowlings", "Fiction", new BigDecimal(800)));
        books.add(new Book(123456788789L, "Kung Fu Panda", "Kung Fu Panda - Part 2", "Adwin Brown", "Comic", new BigDecimal(1100)));

        promotions.add(new Promotion("PRED10", "Educational", 10));
        promotions.add(new Promotion("PRCO20", "Comic", 20));
    }

    @Test
    public void testSave() {
        Request request = new Request(books, null);
        ResponseEntity<Response> entity = controller.save(request);
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 3 && entity.getStatusCodeValue() == 200);
    }

    @Test
    public void testFindAllBooks() {
        testSave();
        ResponseEntity<Response> entity = controller.findAllBooks();
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 3 && entity.getStatusCodeValue() == 200);
    }

    @Test
    public void testFindByISBN() {
        testSave();
        ResponseEntity<Response> entity = controller.findByISBN(123456788787L);
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 1 && entity.getStatusCodeValue() == 200);
    }

    @Test
    public void testFailToFindByISBN() {
        testSave();
        ResponseEntity<Response> entity = controller.findByISBN(1234567887L);
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 0 && entity.getStatusCodeValue() == 404);
    }

    @Test
    public void testFindByAuthor() {
        testSave();
        ResponseEntity<Response> entity = controller.findByAuthor("J K Rowlings");
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 1 && entity.getStatusCodeValue() == 200);
    }

    @Test
    public void testFailToFindByAuthor() {
        testSave();
        ResponseEntity<Response> entity = controller.findByAuthor("George W Bush");
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 0 && entity.getStatusCodeValue() == 404);
    }

    @Test
    public void testFindByName() {
        testSave();
        ResponseEntity<Response> entity = controller.findByName("Harry Potter");
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 1 && entity.getStatusCodeValue() == 200);
    }

    @Test
    public void testFailToFindByName() {
        testSave();
        ResponseEntity<Response> entity = controller.findByName("Citylife");
        Assertions.assertTrue(entity != null && entity.getBody().getBooks().size() == 0 && entity.getStatusCodeValue() == 404);
    }

    @Test
    public void testDelete() {
        testSave();
        ResponseEntity<Response> entity = controller.delete(123456788787L);
        Assertions.assertTrue(entity != null && entity.getBody().getMessage().equalsIgnoreCase("Book deleted") && entity.getStatusCodeValue() == 200);
    }

    @Test
    public void testCheckout() {
        Request request = new Request(books, "PRED10");
        ResponseEntity<Response> entity = controller.checkout(request);
        Assertions.assertTrue(entity != null && entity.getBody().getTotalPrice() == 2170 && entity.getStatusCodeValue() == 200);
    }

    @Test
    public void testExceptionHandling() {
        TransactionSystemException exception = Assertions.assertThrows(TransactionSystemException.class, () ->{
            books.add(new Book(3456, null, "C++ Tutorial", "Keithy Seira", "Educational", new BigDecimal(300)));
            Request request = new Request(books, null);
            ResponseEntity<Response> entity = controller.save(request);
        });
        Assertions.assertTrue(exception.getCause().getCause() instanceof ConstraintViolationException);
    }

}
