package com.book.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @NotNull(message = "isbn is mandatory")
    @Min(value = 1, message = "isbn must be greater than or equal to 1")
    private long isbn;

    @NotEmpty(message = "name is mandatory")
    private String name;

    @NotEmpty(message = "description is mandatory")
    private String description;

    @NotEmpty(message = "author is mandatory")
    private String author;

    @NotEmpty(message = "type is mandatory")
    private String type;

    @NotNull(message = "price is mandatory")
    @Digits(message = "Price must be a digit", fraction = 6, integer = 6)
    private BigDecimal price;

}
