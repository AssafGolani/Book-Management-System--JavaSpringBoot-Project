package com.example.demo.mapper;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.BookModel;
import com.example.demo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 Mappers: Convert entities to DTOs and vice versa.
 BookMapper and AuthorMapper use MapStruct to automate this conversion.
 */
@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);
    BookModel toModel(Book book);
}
