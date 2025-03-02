package ro.itschool.library_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.dto.CategoryDto;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Category;
import ro.itschool.library_manager.service.AuthorService;


import java.util.List;

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
                .body("Book created successfully");
    }

//    @GetMapping("/category")
//    public List<AuthorDto> getAuthorsByCategoryName(@RequestParam CategoryDto categoryDtoName) {
//        return authorService.getAuthorsByCategoryName(categoryDtoName);
//    }

}
