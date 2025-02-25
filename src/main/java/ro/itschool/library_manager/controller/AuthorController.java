package ro.itschool.library_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.service.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private final CategoryService categoryService;

    public AuthorController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Author> getAuthorsByCategoryName(@RequestParam String categoryName) {
        return categoryService.getAuthorsByCategoryName(categoryName);
    }
}
