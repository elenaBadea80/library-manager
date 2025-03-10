
package ro.itschool.library_manager.service;

import org.springframework.stereotype.Service;
import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.repository.AuthorRepository;
import ro.itschool.library_manager.persistence.repository.BookRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ObjectMapper<AuthorDto, Author> authorMapper;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository,
                         ObjectMapper<AuthorDto, Author> authorMapper, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookRepository = bookRepository;
    }

    public void createAuthor(AuthorDto authorDto) {
        Author author = authorMapper.mapToEntity(authorDto);

        authorRepository.save(author);
    }

    public List<AuthorDto> getAuthorsByCategoryBook(String categoryName) {
        List<Book> books = bookRepository.findBooksByCategoryBook(categoryName);
        Set<Author> authors = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .collect(Collectors.toSet());
        return authors.stream()
                .map(authorMapper::mapToDto)
                .toList();
    }

    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorDto> getAuthors() {
        List<Author> allAuthors = authorRepository.findAll();

        return allAuthors.stream()
                .map(authorMapper::mapToDto)
                .toList();
    }
}