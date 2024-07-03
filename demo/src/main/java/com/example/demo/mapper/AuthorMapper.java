package com.example.demo.mapper;

import com.example.demo.model.AuthorModel;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mappers: Convert entities to DTOs and vice versa.
 * BookMapper and AuthorMapper use MapStruct to automate this conversion.
 */
@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO authorDTO);

    AuthorModel toModel(Author author);
}
