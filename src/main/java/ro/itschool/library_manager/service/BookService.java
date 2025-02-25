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

    public Book getBookById(UUID id) {
        return  bookRepository.findById(id).orElseThrow();
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<BookDto> getBooksByTitleAndCategory(String title, String category) {
        List<Book> books = bookRepository.findBooksByTitleAndCategoryJpql(title, category);


        return books.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    public List<BookDto> getBooksByOneTitle(String title) {
        List<Book> books = bookRepository.findBooksByOneTitleJpql(title);


        return books.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }
}
