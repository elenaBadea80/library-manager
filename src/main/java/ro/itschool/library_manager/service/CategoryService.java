package ro.itschool.library_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Category;
import ro.itschool.library_manager.persistence.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

     private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Author> getAuthorsByCategoryName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category != null) {
            return category.getAuthors();
        }
        return List.of();
    }
}
