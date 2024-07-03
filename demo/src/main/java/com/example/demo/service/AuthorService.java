package com.example.demo.service;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.entity.Author;
import com.example.demo.repos.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Services: Contain business logic and interact with repositories.
 * BookService and AuthorService perform operations on books and authors.
 */
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(AuthorMapper.INSTANCE::toDTO)
                .orElse(null);
    }

    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        Author author = AuthorMapper.INSTANCE.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.INSTANCE.toDTO(savedAuthor);
    }
}
