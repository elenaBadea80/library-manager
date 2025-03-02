package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.library_manager.persistence.entity.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

//    List<Author> getAuthorsByCategoryName(String categoryName);

//    @Query("SELECT a FROM Author a JOIN a.categories c WHERE c.categoryName = :categoryName")
//    List<Author> findAuthorsByCategoryName(@Param("categoryName") String categoryName);
}

