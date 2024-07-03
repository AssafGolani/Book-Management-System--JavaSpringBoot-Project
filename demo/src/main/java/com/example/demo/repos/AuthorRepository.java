package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Author;
import org.springframework.stereotype.Repository;

/**
 * Repositories: Provide CRUD operations on entities.
 * BookRepository and AuthorRepository extend JpaRepository for database access.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
