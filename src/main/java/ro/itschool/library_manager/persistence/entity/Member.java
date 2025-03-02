package ro.itschool.library_manager.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String memberName;
    private String email;

    @ManyToMany(mappedBy = "members")
    private Set<Book> books;

    @JsonManagedReference
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Returns> returns;
}
