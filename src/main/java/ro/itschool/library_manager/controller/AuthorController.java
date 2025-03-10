package ro.itschool.library_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.service.AuthorService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDto authorDto) {
        authorService.createAuthor(authorDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Author created successfully");
    }

    @GetMapping("/category")
    public List<AuthorDto> getAuthorsByCategoryBook(@RequestParam String name) {
        return authorService.getAuthorsByCategoryBook(name);
    }

    @GetMapping
    public List<AuthorDto> getAuthors() {
        List<AuthorDto> authors = authorService.getAuthors();
        return authorService.getAuthors();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Author deleted successfully");
    }
}