package com.example.demo.controllers;

import com.example.demo.Errors.ResourceNotFoundException;
import com.example.demo.dto.BookDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.BookService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Controllers: Handle HTTP requests and responses.
 * BookController and AuthorController manage endpoints for book and author
 * operations.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        if (bookDTO != null) {
            return ResponseEntity.ok(bookDTO);
        } else {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        try {
            BookDTO createdBook = bookService.addBook(bookDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (Exception e) {
            throw new RuntimeException("Error adding book: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public CompletableFuture<ResponseEntity<String>> searchBooksByTitle(@RequestParam String title) {
        return bookService.searchBooksByTitle(title)
                .thenApply(result -> ResponseEntity.ok(result))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()));
    }

    // New endpoint to search and save books from the external API
    @GetMapping("/searchAndSave")
    public CompletableFuture<ResponseEntity<List<BookDTO>>> searchAndSaveBooksByTitle(@RequestParam String title) {
        return bookService.searchAndSaveBooksByTitle(title)
                .thenApply(result -> ResponseEntity.ok(result))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }
}
