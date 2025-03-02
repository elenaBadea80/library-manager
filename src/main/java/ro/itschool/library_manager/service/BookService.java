
package ro.itschool.library_manager.service;

import org.springframework.stereotype.Service;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.repository.BookRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper<BookDto, Book> bookMapper;

    public BookService(BookRepository bookrepository,
                       ObjectMapper<BookDto, Book> bookMapper) {
        this.bookRepository = bookrepository;
        this.bookMapper = bookMapper;
    }

    public BookDto getBookById(UUID id) {
        Book referenceById = bookRepository.getReferenceById(id);

        return bookMapper.mapToDto(referenceById);
    }

    public List<BookDto> getBooks(){
        List<Book> allBooks = bookRepository.findAll();

        return allBooks.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    public void createBook(BookDto bookDto) {
        Book book = bookMapper.mapToEntity(bookDto);
        bookRepository.save(book);
    }

    public List<BookDto> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitleJpql(title);

        return books.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    public List<BookDto> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findBooksByAuthorJpql(author);

        return books.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    public void deleteBookById(UUID id) {
        // DELETE FROM product WHERE id = ?
        bookRepository.deleteById(id);
    }

    // TODO: Still in progress
    public void updateBook(BookDto bookDto, BookDto existingBookDto) {
        if (bookDto.getTitle() != null) {
            existingBookDto.setTitle(bookDto.getTitle());
        }

        if (bookDto.getCategoryBook() != null) {
            existingBookDto.setCategoryBook(bookDto.getCategoryBook());
        }

        if (bookDto.getYear() != 0) {
            existingBookDto.setYear(bookDto.getYear());
        }

        if (bookDto.getAuthor() != null) {
            existingBookDto.setAuthor(bookDto.getAuthor());
        }
    }

    // TODO: Still in progress
    public void replaceBook(BookDto bookDto, BookDto existingBookDto) {
        existingBookDto.setTitle(bookDto.getTitle());
        existingBookDto.setCategoryBook(bookDto.getCategoryBook());
        existingBookDto.setYear(bookDto.getYear());
        existingBookDto.setAuthor(bookDto.getAuthor());
    }
}
