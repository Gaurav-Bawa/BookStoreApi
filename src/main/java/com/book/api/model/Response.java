package com.book.api.model;

import com.book.api.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double totalPrice;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Book> books;
}
