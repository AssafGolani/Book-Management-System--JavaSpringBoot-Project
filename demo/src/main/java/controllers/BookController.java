package controllers;

import Errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Book;
import service.BookService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        if (book != null){
            return ResponseEntity.ok(book);
        }else{
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try{
            Book createdBook = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        }catch (Exception e){
            throw new RuntimeException("Error adding book: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        }else{
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public CompletableFuture<ResponseEntity<String>> searchBooksByTitle(@RequestParam String title){
        return bookService.serachBooksByTitle(title)
                .thenApply(result -> ResponseEntity.ok(result))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()));
    }
}
