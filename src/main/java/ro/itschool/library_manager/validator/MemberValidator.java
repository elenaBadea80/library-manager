package ro.itschool.library_manager.validator;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.MemberDto;
import ro.itschool.library_manager.exception.InvalidMemberException;


@Component
public class MemberValidator {

    public void memberValidate(MemberDto member) {
        if (member == null) {
            throw new InvalidMemberException("Member cannot be null");
        }

        if (member.getMemberName() == null || member.getMemberName().isEmpty()) {
            throw new InvalidMemberException("Member name cannot be null or empty");
        }

        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new InvalidMemberException ("Member mail cannot be empty");
        }
    }
}
