package ro.itschool.library_manager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.library_manager.dto.ReturnsDto;
import ro.itschool.library_manager.mapper.impl.ReturnsMapper;
import ro.itschool.library_manager.persistence.entity.Returns;
import ro.itschool.library_manager.service.ReturnsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/returns")
public class ReturnsController {

    private final ReturnsService returnsService;
    private final ReturnsMapper returnsMapper;

    public ReturnsController(ReturnsService returnsService,
                             ReturnsMapper returnsMapper) {
        this.returnsService = returnsService;
        this.returnsMapper = returnsMapper;
    }

    @PostMapping
    public ResponseEntity<Returns> createReturn(@RequestBody ReturnsDto returnsDto) {
        Returns createdReturn = returnsService.saveReturn(returnsDto);
        return new ResponseEntity<>(createdReturn, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Returns> updateReturn(@PathVariable UUID id, @RequestBody ReturnsDto returnsDto) {
        Returns updatedReturn = returnsService.updateReturn(id, returnsDto);
        return new ResponseEntity<>(updatedReturn, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Returns>> getAllReturns() {
        List<Returns> returns = returnsService.getAllReturns();
        return new ResponseEntity<>(returns, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnsDto> getBookReturnedById(@PathVariable UUID id) {
        Returns returnById = returnsService.getReturnById(id);
        return ResponseEntity.ok(returnsMapper.mapToDto(returnById));
    }

}
