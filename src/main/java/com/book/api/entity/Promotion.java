package com.book.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTION")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    @Id
    private String promoCode;

    @Column(length = 100)
    private String bookType;

    private double percent;

}
