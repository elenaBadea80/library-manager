package ro.itschool.library_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.dto.MemberDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.entity.Member;
import ro.itschool.library_manager.persistence.repository.MemberRepository;
import ro.itschool.library_manager.service.MemberService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ObjectMapper<MemberDto, Member> memberMapper;

    public MemberController(MemberService memberService,
                            MemberRepository memberRepository,
                            ObjectMapper<MemberDto, Member> memberMapper) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @GetMapping
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable UUID id) {
        MemberDto memberDto = memberService.getMemberById(id);
        if (memberDto != null) {
            return ResponseEntity.ok(memberDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping
//    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
//        memberService.createMember(memberDto);
//        return ResponseEntity.status(201).body(memberDto);
//    }

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        try {
            memberService.createMember(memberDto);
            return ResponseEntity.status(201).body(memberDto);
        } catch (InvalidPayloadException e) {
            // Tratarea excepției de validare a payload-ului
            System.out.println("Eroare de validare: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // Tratarea altor excepții
            System.out.println("A apărut o eroare: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable UUID id,
                                              @RequestBody MemberDto memberDto) {
        MemberDto existingMemberDto = memberService.getMemberById(id);

        if (existingMemberDto == null) {
            return ResponseEntity.notFound().build();
        }

        memberService.updateMember(memberDto, existingMemberDto);

        return ResponseEntity.ok(existingMemberDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<MemberDto> findMembersByName(@RequestParam String memberName) {
        List<Member> memberByName = memberRepository.findMembersByMemberName(memberName);
        return memberByName.stream()
                .map(memberMapper :: mapToDto)
                .toList();
    }

    @GetMapping("/book/{bookTitle}")
    public List<MemberDto> findMembersByBookTitle(@PathVariable String bookTitle) {
        List<Member> memberByBookTitle = memberRepository.findMembersByBookTitle(bookTitle);
        return memberByBookTitle.stream()
                .map(memberMapper :: mapToDto)
                .toList();
    }
}
