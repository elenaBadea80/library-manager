package ro.itschool.library_manager.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.MemberDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Member;

@Component
public class MemberMapper implements ObjectMapper<MemberDto, Member> {

    @Override
    public MemberDto mapToDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getMemberName(),
                member.getEmail()
        );
    }

    @Override
    public Member mapToEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberName(memberDto.getMemberName());
        member.setEmail(memberDto.getEmail());
        return member;
    }
}
