package ro.itschool.library_manager.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.repository.AuthorRepository;
import ro.itschool.library_manager.persistence.repository.CategoryRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class BookMapper implements ObjectMapper<BookDto, Book> {

    private final AuthorRepository authorRepository;

    public BookMapper(AuthorRepository authorRepository,
                      AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Book mapToEntity(BookDto bookDto) {
        Book book = new Book();

        book.setTitle(bookDto.getTitle());
        Set<Author> authors = new HashSet<>();
        bookDto.getAuthors()
                .forEach(a -> {
                    Optional<Author> authorById = authorRepository.findById(a);
                    authors.add(authorById.orElseThrow());
                });
        book.setAuthors(authors);
        book.setCategoryBook(bookDto.getCategoryBook());
        book.setYear(bookDto.getYear());

        return book;
    }

    @Override
    public BookDto mapToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getCategoryBook(),
                book.getAuthors().stream()
                        .map(Author::getId)
                        .toList(),
                book.getYear()
        );
    }

}
