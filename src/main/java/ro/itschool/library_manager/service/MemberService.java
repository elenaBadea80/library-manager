package ro.itschool.library_manager.service;

import org.springframework.stereotype.Service;
import ro.itschool.library_manager.dto.MemberDto;
import ro.itschool.library_manager.mapper.impl.MemberMapper;
import ro.itschool.library_manager.persistence.entity.Member;
import ro.itschool.library_manager.persistence.repository.MemberRepository;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    public final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository,
                         MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public List<MemberDto> getAllMembers() {
        List<Member> allMembers = memberRepository.findAll();

        return allMembers.stream()
                .map(memberMapper::mapToDto)
                .toList();
    }

    public MemberDto getMemberById(UUID id) {
        Member referenceById = memberRepository.getReferenceById(id);

        return memberMapper.mapToDto(referenceById);
    }

    public void createMember(MemberDto memberDto) {
        Member member = memberMapper.mapToEntity(memberDto);

        memberRepository.save(member);
    }

    public void updateMember(MemberDto memberDto, MemberDto existingMemberDto) {
        if (memberDto.getMemberName() != null) {
            existingMemberDto.setMemberName(memberDto.getMemberName());
        }

        if (memberDto.getEmail() != null) {
            existingMemberDto.setEmail(memberDto.getEmail());
        }
    }

    public void deleteMember(UUID id) {
        memberRepository.deleteById(id);
    }

    public List<MemberDto> findMembersByName(String memberName) {
        List<Member> members = memberRepository.findMembersByMemberName(memberName);
        return members.stream()
                .map(memberMapper::mapToDto)
                .toList();
    }

    public List<MemberDto> findMembersByBookTitle(String bookTitle) {
        List<Member> members = memberRepository.findMembersByBookTitle(bookTitle);
        return members.stream()
                .map(memberMapper::mapToDto)
                .toList();
    }
}