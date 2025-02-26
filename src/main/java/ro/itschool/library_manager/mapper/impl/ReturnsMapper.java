package ro.itschool.library_manager.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.ReturnsDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Returns;

@Component
public class ReturnsMapper implements ObjectMapper<ReturnsDto, Returns> {

    @Override
    public ReturnsDto mapToDto(Returns returns) {
        return new ReturnsDto(
                returns.getId(),
                returns.getBook(),
                returns.getMember()
        );
    }

    @Override
    public Returns mapToEntity(ReturnsDto returnsDto) {
        Returns returns = new Returns();
        returns.setId(returnsDto.getId());
        returns.setBook(returnsDto.getBook());
        returns.setMember(returnsDto.getMember());
        return returns;
    }

}
