package ro.itschool.library_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.MemberDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
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

    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody MemberDto memberDto) {

        memberService.createMember(memberDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Member created successfully");
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
    public ResponseEntity<String> deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Member deleted successfully");
    }

    @GetMapping("/title")
    public List<MemberDto> getMembersByBookTitle(@RequestParam String title) {
        return memberService.getMembersByBookTitle(title);
    }
}
