package ro.itschool.library_manager.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.ReturnsDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Returns;

@Component
public class ReturnsMapper implements ObjectMapper<ReturnsDto, Returns> {

    private final MemberMapper memberMapper;
    private final BookMapper bookMapper;

    public ReturnsMapper(MemberMapper memberMapper,
                         BookMapper bookMapper) {
        this.memberMapper = memberMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public ReturnsDto mapToDto(Returns returns) {
        ReturnsDto returnsDto = new ReturnsDto();
        returnsDto.setId(returns.getId());
        returnsDto.setMemberReturns(memberMapper.mapToDto(returns.getMember()));
        returnsDto.setBookReturns(bookMapper.mapToDto(returns.getBook()));
        return returnsDto;
    }

    @Override
    public Returns mapToEntity(ReturnsDto returnsDto) {
        Returns returns = new Returns();
        returns.setId(returnsDto.getId());
        returns.setMember(memberMapper.mapToEntity(returnsDto.getMemberReturns()));
        returns.setBook(bookMapper.mapToEntity(returnsDto.getBookReturns()));
        return returns;
    }
}