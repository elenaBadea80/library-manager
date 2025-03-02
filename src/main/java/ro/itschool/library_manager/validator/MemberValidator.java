package ro.itschool.library_manager.validator;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.MemberDto;
import ro.itschool.library_manager.exception.InvalidPayloadException;


@Component
public class MemberValidator {

    public void validateClient(MemberDto member) {
        if (member == null) {
            throw new InvalidPayloadException("Member cannot be null");
        }

        if (member.getMemberName() == null || member.getMemberName().isEmpty()) {
            throw new InvalidPayloadException("Member name cannot be null or empty");
        }
    }
}
