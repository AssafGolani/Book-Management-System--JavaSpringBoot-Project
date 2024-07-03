package com.example.demo.dto;

import lombok.Data;

import java.util.Set;

/**
 * DTOs (Data Transfer Objects): Used to transfer data between layers to avoid
 * exposing the entity structure.
 * BookDTO and AuthorDTO are used to transfer book and author data.
 */
@Data
public class AuthorDTO {
    private Long id;
    private String name;
    private Set<BookDTO> books;
}
