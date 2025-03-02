
package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Book;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> getBooksByTitle(String title);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
    List<Book> findBooksByAuthorJpql(UUID authorId);

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> findBooksByTitleJpql(String title);

    @Query(value = "SELECT * FROM book WHERE book_title = ?1", nativeQuery = true)
    List<Book> findBooksByTitleNative(String title);

    @Query("SELECT b FROM Book b WHERE b.categoryBook = :categoryName")
    List<Book> findBooksByCategoryName(@Param("categoryName") String categoryName);

}
