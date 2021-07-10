package com.book.api.model;

import com.book.api.entity.Book;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @NonNull
    @NotEmpty(message = "pass list of books")
    private List<Book> books;

    private String promoCode;

}
