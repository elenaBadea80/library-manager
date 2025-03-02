package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.entity.Category;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, UUID> {

    // Găsește toți autorii asociați cu o anumită categorie după numele categoriei
    @Query("SELECT a FROM Author a WHERE a.category.categoryName = :categoryName")
    List<Author> findAuthorsByCategoryName(@Param("categoryName") String categoryName);

    // Găsește toate cărțile asociate cu o anumită categorie după numele categoriei
    @Query("SELECT b FROM Book b WHERE b.category.categoryName = :categoryName")
    List<Book> findBooksByCategoryName(@Param("categoryName") String categoryName);
}
