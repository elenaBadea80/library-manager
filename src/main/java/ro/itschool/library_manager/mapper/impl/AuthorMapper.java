package ro.itschool.library_manager.mapper.impl;

import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Author;

public class AuthorMapper implements ObjectMapper<AuthorDto, Author> {

    @Override
    public AuthorDto mapToDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getAuthorName()
        );
    }

    @Override
    public Author mapToEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setAuthorName(authorDto.getAuthorName());
        return author;
    }
}

