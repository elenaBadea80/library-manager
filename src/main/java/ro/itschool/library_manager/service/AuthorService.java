package ro.itschool.library_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Category;
import ro.itschool.library_manager.persistence.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthorsByCategoryName(Category categoryName) {
        return authorRepository.findAll().stream()
                .filter(author -> author.getCategories().contains(categoryName))
                .toList();
    }
}