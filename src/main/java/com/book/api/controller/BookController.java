package com.book.api.controller;


import com.book.api.model.Request;
import com.book.api.model.Response;
import com.book.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid Request request) {
        return service.save(request.getBooks());
    }

    @GetMapping("/books")
    public ResponseEntity<Response> findAllBooks() {
        return service.findAllBooks();
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<Response> findByISBN(@PathVariable long isbn) {
        return service.findByISBN(isbn);
    }

    @GetMapping("/book/name")
    public ResponseEntity<Response> findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @GetMapping("/book/author")
    public ResponseEntity<Response> findByAuthor(@RequestParam String author) {
        return service.findByAuthor(author);
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<Response> delete(@PathVariable long isbn) {
        return service.delete(isbn);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Response> checkout(@RequestBody Request request) {
        return service.checkout(request);
    }

}
