package ro.itschool.library_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {

    private UUID id;
    private String memberName;
    private String email;
}
