package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Book;
import repos.BookRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BookService {
    @Autowired
    private ExternalBooksService externalBooksService;
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookInfo){
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null){
            book.setTitle(bookInfo.getTitle());
            book.setAuthor(bookInfo.getAuthor());
            book.setIsbn(bookInfo.getIsbn());
            book.setPublisher(bookInfo.getPublisher());
            book.setYear(bookInfo.getYear());
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public CompletableFuture<String> serachBooksByTitle(String title){
        return externalBooksService.searchBooksByTitle(title);
    }
}
