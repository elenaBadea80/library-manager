package ro.itschool.library_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.entity.Member;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnsDto {

    private UUID id;

    private BookDto bookReturns;
    private MemberDto memberReturns;

}
