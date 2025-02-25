package ro.itschool.library_manager.mapper.impl;

import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.dto.ReturnsDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Returns;

public class ReturneMapper implements ObjectMapper<ReturnsDto, Returns> {

    @Override
    public ReturnsDto mapToDto(Returns returns) {
        return new ReturnsDto(
                returns.getId(),
                returns.getBookId(),
                returns.getMemberId()
        );
    }

    @Override
    public Returns mapToEntity(ReturnsDto returnsDto) {
        Returns returns = new Returns();

        returns.setBookId(returnsDto.getBookId());
        returns.setMemberId(returnsDto.getMemberId());

        return returns;
    }
}
