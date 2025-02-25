package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.itschool.library_manager.persistence.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

//    List<Author> getAuthorsByCategoryName(String categoryName);

    @Query("SELECT a FROM Author a JOIN a.categories c WHERE c.categoryName = :categoryName")
    List<Author> findAuthorsByCategoryName(@Param("categoryName") String categoryName);

//    @Query("SELECT a FROM Author a WHERE a.category = ?1")
//    List<Author> findAuthorsByCategoryNameJpql(String category);
//
//    @Query(value = "SELECT * FROM author WHERE author_title = ?1", nativeQuery = true)
//    List<Author> findAuthorByCategoryNameNative(String category);
}

