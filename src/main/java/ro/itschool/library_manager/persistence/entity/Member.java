package ro.itschool.library_manager.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String memberName;
    private String email;

    @ManyToMany(mappedBy = "members")
    private Set<Book> books;

    @OneToMany(mappedBy = "member")
    private Set<Returns> returns;
}
