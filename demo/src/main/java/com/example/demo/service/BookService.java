package com.example.demo.service;


import com.example.demo.dto.BookDTO;
import com.example.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.repos.BookRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Services: Contain business logic and interact with repositories.
 * BookService and AuthorService perform operations on books and authors.
 */
@Service
public class BookService {
    @Autowired
    private ExternalBooksService externalBooksService;
    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(BookMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id){
        return bookRepository.findById(id)
                .map(BookMapper.INSTANCE::toDTO)
                .orElse(null);
    }

    public BookDTO addBook(BookDTO bookDTO){
        Book book = BookMapper.INSTANCE.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMapper.INSTANCE.toDTO(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO){
        return bookRepository.findById(id)
                .map(existingBook -> {
                    Book book = BookMapper.INSTANCE.toEntity(bookDTO);
                    book.setId(existingBook.getId());
                    Book updatedBook = bookRepository.save(book);
                    return BookMapper.INSTANCE.toDTO(updatedBook);
                })
                .orElse(null);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public CompletableFuture<String> searchBooksByTitle(String title) {
        return externalBooksService.searchBooksByTitle(title)
                .thenApply(result -> result.stream()
                        .map(BookDTO::getTitle)
                        .collect(Collectors.joining(", ")));
    }

    public CompletableFuture<List<BookDTO>> searchAndSaveBooksByTitle(String title) {
        return externalBooksService.searchBooksByTitle(title)
                .thenApply(bookDTOs -> {
                    // Save books to local database
                    List<Book> books = bookDTOs.stream()
                            .map(BookMapper.INSTANCE::toEntity)
                            .collect(Collectors.toList());
                    bookRepository.saveAll(books);
                    return bookDTOs;
                });
    }
}
