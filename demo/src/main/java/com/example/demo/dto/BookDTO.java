package com.example.demo.dto;

import lombok.Data;

import java.util.Set;

/**
 * DTOs (Data Transfer Objects): Used to transfer data between layers to avoid
 * exposing the entity structure.
 * BookDTO and AuthorDTO are used to transfer book and author data.
 */
@Data
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private String publisher;
    private int year;
    private Set<AuthorDTO> authors;
}
