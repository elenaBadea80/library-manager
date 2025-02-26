package ro.itschool.library_manager.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.CategoryDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Category;

@Component
public class CategoryMapper implements ObjectMapper<CategoryDto, Category> {

    @Override
    public CategoryDto mapToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getCategoryName()
        );
    }

    @Override
    public Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
