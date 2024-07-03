package com.example.demo.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;
/**
 * Models: Representation models for response objects, often used with HATEOAS.
 */
@Data
public class BookModel extends RepresentationModel<BookModel> {
    private Long id;
    private String title;
    private String isbn;
    private String publisher;
    private int year;
    private Set<AuthorModel> authors;
}