package ro.itschool.library_manager.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    @ManyToMany(mappedBy = "categories")
    private List<Author> authors;
}
