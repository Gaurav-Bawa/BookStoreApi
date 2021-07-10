package com.book.api.repository;

import com.book.api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Book findByName(String name);

    public List<Book> findByAuthor(String author);
}
