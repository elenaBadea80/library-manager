package ro.itschool.library_manager.service;

import org.springframework.stereotype.Service;
import ro.itschool.library_manager.dto.CategoryDto;
import ro.itschool.library_manager.mapper.impl.CategoryMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.entity.Category;
import ro.itschool.library_manager.persistence.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
           List<Category> allCategories = categoryRepository.findAll();

            return allCategories.stream()
                    .map(categoryMapper::mapToDto)
                    .toList();
        }

    public CategoryDto getCategoryById(UUID id) {
        Category referenceById = categoryRepository.getReferenceById(id);
        return categoryMapper.mapToDto(referenceById);
    }

    public void createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.mapToEntity(categoryDto);

        categoryRepository.save(category);
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    public List<Author> findAuthorsByCategoryName(String categoryName) {
        return categoryRepository.findAuthorsByCategoryName(categoryName);
    }

    public List<Book> findBooksByCategoryName(String categoryName) {
        return categoryRepository.findBooksByCategoryName(categoryName);
    }
}
