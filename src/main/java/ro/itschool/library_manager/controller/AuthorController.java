package ro.itschool.library_manager.controller;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/category")
    public List<Author> getAuthorsByCategoryName(@RequestParam Category categoryName) {
        return authorService.getAuthorsByCategoryName(categoryName);
    }

}
