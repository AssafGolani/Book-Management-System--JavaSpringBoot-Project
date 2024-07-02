package controllers;

import Errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Author;
import service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author author = authorService.getAuthorById(id);
        if(author != null){
            return ResponseEntity.ok(author);
        }
        throw new ResourceNotFoundException("Author not found with id: " + id);
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        try{
            Author createdAuthor = authorService.addAuthor(author);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
        }catch (Exception e){
            throw new RuntimeException("Error adding author: " + e.getMessage());
        }
    }
}
