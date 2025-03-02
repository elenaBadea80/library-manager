
package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.itschool.library_manager.persistence.entity.Author;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.categoryBook = ?1")
    List<Author> findAuthorsByCategoryName(String categoryName);
}