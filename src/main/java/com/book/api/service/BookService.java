package com.book.api.service;

import com.book.api.entity.Book;
import com.book.api.entity.Promotion;
import com.book.api.model.Request;
import com.book.api.model.Response;
import com.book.api.repository.BookRepository;
import com.book.api.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private PromotionRepository promotionRepository;

    public ResponseEntity<Response> save(List<Book> books) {
        List<Book> bookList = repository.saveAll(books);
        if (books.size() == bookList.size()) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(0, bookList.size() + " RECORDS SAVED SUCCESSFULLY", bookList));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(0, "NOT SAVED", Collections.emptyList()));
    }

    public ResponseEntity<Response> findAllBooks() {
        List<Book> books = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(0, books.size() + " RECORDS FOUND", books));
    }

    public ResponseEntity<Response> findByISBN(long id) {
        Optional<Book> optional = repository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(0, "1 RECORD FOUND", Collections.singletonList(optional.get())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(0, "NO RECORD FOUND", Collections.emptyList()));
        }
    }

    public ResponseEntity<Response> findByName(String name) {
        Book book = repository.findByName(name);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(0, "1 RECORD FOUND", Collections.singletonList(book)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(0, "NO RECORD FOUND", Collections.emptyList()));
        }
    }

    public ResponseEntity<Response> findByAuthor(String author) {
        List<Book> books = repository.findByAuthor(author);
        if (books != null && !books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(0, books.size() + " RECORD FOUND", books));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(0, "NO RECORD FOUND", Collections.emptyList()));
        }
    }

    public ResponseEntity<Response> delete(long isbn) {
        repository.deleteById(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(0, "Book deleted", Collections.emptyList()));
    }

    public ResponseEntity<Response> checkout(Request request) {
        if (request.getPromoCode() != null) {
            Optional<Promotion> optional = promotionRepository.findById(request.getPromoCode());
            if (optional.isPresent()) {
                double totalPrice = request.getBooks().stream().mapToDouble(
                        x -> {
                            double price = 0;
                            if (x.getType().equalsIgnoreCase(optional.get().getBookType())) {
                                price = (x.getPrice().doubleValue() - (x.getPrice().doubleValue() * optional.get().getPercent()) / 100);
                            } else {
                                price = x.getPrice().doubleValue();
                            }
                            return price;
                        }
                ).sum();
                return ResponseEntity.status(HttpStatus.OK).body(new Response(totalPrice, "SUCCESS", request.getBooks()));
            } else {
                double totalPrice = request.getBooks().stream().mapToDouble(x -> x.getPrice().doubleValue()).sum();
                return ResponseEntity.status(HttpStatus.OK).body(new Response(totalPrice, "SUCCESS", request.getBooks()));
            }
        } else {
            double totalPrice = request.getBooks().stream().mapToDouble(x -> x.getPrice().doubleValue()).sum();
            return ResponseEntity.status(HttpStatus.OK).body(new Response(totalPrice, "SUCCESS", request.getBooks()));
        }
    }
}
