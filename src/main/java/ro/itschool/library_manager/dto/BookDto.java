package ro.itschool.library_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private UUID id;

    private String title;
    private String categoryBook;
    private List<UUID> authors;
    private List<UUID> members;
    private int year;
}




