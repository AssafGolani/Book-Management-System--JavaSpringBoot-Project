package com.example.demo.repos;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositories: Provide CRUD operations on entities.
 * BookRepository and AuthorRepository extend JpaRepository for database access.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
