package ro.itschool.library_manager.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.library_manager.persistence.entity.Member;

import java.util.UUID;

public interface ReturnsRepository  extends JpaRepository<Member, UUID> {
}
