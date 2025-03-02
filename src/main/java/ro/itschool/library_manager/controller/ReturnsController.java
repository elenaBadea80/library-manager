package ro.itschool.library_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.ReturnsDto;
import ro.itschool.library_manager.mapper.impl.ReturnsMapper;
import ro.itschool.library_manager.service.ReturnsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/returns")
public class ReturnsController {

    private final ReturnsService returnsService;
    private final ReturnsMapper returnsMapper;

    public ReturnsController(ReturnsService returnsService,
                             ReturnsMapper returnsMapper) {
        this.returnsService = returnsService;
        this.returnsMapper = returnsMapper;
    }


    @GetMapping
    public ResponseEntity<List<ReturnsDto>> getAllReturns() {
        List<ReturnsDto> returnsList = returnsService.getAllReturns();
        return new ResponseEntity<>(returnsList, HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReturnsDto>> getReturnsByBookId(@PathVariable UUID bookId) {
        List<ReturnsDto> returnsList = returnsService.getReturnsByBookId(bookId);
        return new ResponseEntity<>(returnsList, HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ReturnsDto>> getReturnsByMemberId(@PathVariable UUID memberId) {
        List<ReturnsDto> returnsList = returnsService.getReturnsByMemberId(memberId);
        return new ResponseEntity<>(returnsList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReturnsDto> createReturns(@RequestBody ReturnsDto returnsDto) {
        ReturnsDto createdReturns = returnsService.createReturns(returnsDto);
        return new ResponseEntity<>(createdReturns, HttpStatus.CREATED);
    }
}
