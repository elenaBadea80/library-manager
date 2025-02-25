package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.library_manager.persistence.entity.Category;

import java.util.UUID;

public interface CategoryRepository  extends JpaRepository<Category, UUID> {

    Category findByCategoryName(String categoryName);
}
