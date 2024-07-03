package com.example.demo.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

/**
 * Models: Representation models for response objects, often used with HATEOAS.
 */
@Data
public class AuthorModel extends RepresentationModel<AuthorModel> {
    private Long id;
    private String name;
    private Set<BookModel> books;
}
