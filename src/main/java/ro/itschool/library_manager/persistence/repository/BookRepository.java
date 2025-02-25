package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.itschool.library_manager.persistence.entity.Book;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> getBooksByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title = ?1 ")
    List<Book> findBooksByOneTitleJpql(String title);

    @Query("SELECT b FROM Book b WHERE b.title = ?1 and b.category = ?2")
    List<Book> findBooksByTitleAndCategoryJpql(String title, String category);

    @Query(value = "SELECT * FROM book WHERE book_title = ?1 and category = ?2", nativeQuery = true)
    List<Book> findBooksByTitleAndCategoryNative(String title, String category);
}
