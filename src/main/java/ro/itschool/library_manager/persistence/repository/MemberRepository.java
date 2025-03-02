package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Member;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository  extends JpaRepository<Member, UUID> {

    List<Member> findMembersByMemberName(String memberName);

    @Query("SELECT m FROM Member m JOIN m.books b WHERE b.title = ?1")
    List<Member> findMembersByBookTitle(@Param("bookTitle") String bookTitle);

 }
