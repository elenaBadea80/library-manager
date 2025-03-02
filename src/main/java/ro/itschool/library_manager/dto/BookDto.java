package ro.itschool.library_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

public class BookDto {

    private UUID id;

    private String title;
    private String categoryBook;
    private String author;
    private int year;
}

//public record BookDto(UUID id,
//                      String title,
//                      String author,
//                      String category,
//                      int year) {



