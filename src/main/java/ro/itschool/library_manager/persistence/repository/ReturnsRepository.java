package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.itschool.library_manager.persistence.entity.Returns;

import java.util.List;
import java.util.UUID;

public interface ReturnsRepository extends JpaRepository<Returns, UUID> {

    @Query("SELECT r FROM Returns r WHERE r.member.id = ?1")
    List<Returns> findByMemberId(UUID memberId);

    @Query("SELECT r FROM Returns r WHERE r.book.id = ?1")
    List<Returns> findByBookId(UUID bookId);
}
