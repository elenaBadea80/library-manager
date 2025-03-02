
package ro.itschool.library_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.repository.BookRepository;
import ro.itschool.library_manager.service.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ObjectMapper<BookDto, Book> bookMapper;
    private final BookRepository bookRepository;

    private BookController(BookService bookService,
                           ObjectMapper<BookDto, Book> bookMapper,
                           BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/title/{title}")
    public List<BookDto> getBooksByTitle(@PathVariable String title) {
        List<Book> booksByTitle = bookRepository.getBooksByTitle(title);

        return booksByTitle.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    @GetMapping("/author/{author}")
    public List<BookDto> getBooksByAuthor(@PathVariable
                                          String author) {
        List<Book> booksByAuthor = bookRepository.getBooksByAuthor(author);

        return booksByAuthor.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    @GetMapping
    public List<BookDto> getBooks() {
        List<BookDto> books = bookService.getBooks();
        return bookService.getBooks();
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable UUID id) {
        BookDto bookDtoById = bookService.getBookById(id);

        if (bookDtoById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDtoById);
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {
        bookService.createBook(bookDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book created successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteBookById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable UUID id,
                                              @RequestBody BookDto bookDto) {
        BookDto existingBookDto = bookService.getBookById(id);

        if (existingBookDto == null) {
            return ResponseEntity.notFound().build();
        }

        bookService.updateBook(bookDto, existingBookDto);

        return ResponseEntity.ok(existingBookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> replaceBook(@PathVariable UUID id,
                                               @RequestBody BookDto bookDto) {
        BookDto existingBookDto = bookService.getBookById(id);

        if (existingBookDto == null) {
            return ResponseEntity.notFound().build();
        }
        bookService.replaceBook(bookDto, existingBookDto);
        return ResponseEntity.ok(existingBookDto);
    }
}
