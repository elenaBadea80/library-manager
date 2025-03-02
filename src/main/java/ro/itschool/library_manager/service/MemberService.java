package ro.itschool.library_manager.service;

import org.springframework.stereotype.Service;
import ro.itschool.library_manager.dto.MemberDto;
import ro.itschool.library_manager.mapper.impl.MemberMapper;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.entity.Member;
import ro.itschool.library_manager.persistence.repository.BookRepository;
import ro.itschool.library_manager.persistence.repository.MemberRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    public final MemberMapper memberMapper;
    private final BookRepository bookRepository;

    public MemberService(MemberRepository memberRepository,
                         MemberMapper memberMapper, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.bookRepository = bookRepository;
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

    public List<MemberDto> getMembersByBookTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitle(title);
        Set<Member> members = books.stream()
                .flatMap(book -> book.getMembers().stream())
                .collect(Collectors.toSet());
        return members.stream()
                .map(memberMapper::mapToDto)
                .toList();
    }
}