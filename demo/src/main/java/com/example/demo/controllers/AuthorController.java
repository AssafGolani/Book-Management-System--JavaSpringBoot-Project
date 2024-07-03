package com.example.demo.controllers;

import com.example.demo.Errors.ResourceNotFoundException;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controllers: Handle HTTP requests and responses.
 BookController and AuthorController manage endpoints for book and author operations.
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping
    public List<AuthorDTO> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id){
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        if (authorDTO != null) {
            return ResponseEntity.ok(authorDTO);
        } else {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO){
        try {
            AuthorDTO createdAuthor = authorService.addAuthor(authorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
        } catch (Exception e) {
            throw new RuntimeException("Error adding author: " + e.getMessage());
        }
    }
}
