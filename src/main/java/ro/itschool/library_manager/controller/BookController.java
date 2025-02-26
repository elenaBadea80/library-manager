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

    @GetMapping("/{title}")
    public List<BookDto> getBooksByTitle(String title) {
        List<Book> booksByTitle = bookRepository.getBooksByTitle(title);

        return booksByTitle.stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books.stream()
                .map(bookMapper::mapToDto)
                .toList());
    }

    @GetMapping("/title")
    public List<BookDto> getBooksByOneTitle(@RequestParam String title) {
        return bookService.getBooksByOneTitle(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable UUID id) {
        Book bookById = bookService.getBookById(id);

        return ResponseEntity.ok(bookMapper.mapToDto(bookById));
    }

    @GetMapping("/title/category")
    public List<BookDto> getBooksByTitleAndCategory(@RequestParam String title,
                                                    @RequestParam String category) {
        return bookService.getBooksByTitleAndCategory(title, category);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        Book mappedBook = bookMapper.mapToEntity(bookDto);
        Book createBook = bookService.createBook(mappedBook);
        return new ResponseEntity<>(bookMapper
                .mapToDto(createBook),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteBookById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id,
                                           @RequestBody BookDto bookDto) {
        Book existingBookDto = bookService.getBookById(id);

        if (existingBookDto == null) {
            return ResponseEntity.notFound().build();
        }

        bookService.updateBook(bookDto, existingBookDto);

        return ResponseEntity.ok(existingBookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> replaceBook(@PathVariable UUID id,
                                            @RequestBody BookDto bookDto) {
        Book existingBookDto = bookService.getBookById(id);

        if (existingBookDto == null) {
            return ResponseEntity.notFound().build();
        }
        bookService.replaceBook(bookDto, existingBookDto);
        return ResponseEntity.ok(existingBookDto);
    }
}
