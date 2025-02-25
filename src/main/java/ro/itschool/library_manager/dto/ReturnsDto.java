package ro.itschool.library_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ReturnsDto {

    private UUID id;
    private String bookId;
    private String memberId;

}
