package ro.itschool.library_manager.service;

import org.springframework.stereotype.Service;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.dto.ReturnsDto;
import ro.itschool.library_manager.mapper.impl.ReturnsMapper;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.entity.Returns;
import ro.itschool.library_manager.persistence.repository.ReturnsRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ReturnsService {

    private final ReturnsRepository returnsRepository;
    private final ReturnsMapper returnsMapper;

    public ReturnsService(ReturnsRepository returnsRepository,
                          ReturnsMapper returnsMapper) {
        this.returnsRepository = returnsRepository;
        this.returnsMapper = returnsMapper;
    }

    public List<ReturnsDto> getAllReturns() {
        List<Returns> returnsList = returnsRepository.findAll();
        return returnsList.stream().map(returnsMapper::mapToDto).collect(Collectors.toList());
    }

    public List<ReturnsDto> getReturnsByBookId(UUID bookId) {
        List<Returns> returnsList = returnsRepository.findByBookId(bookId);
        return returnsList.stream().map(returnsMapper::mapToDto).collect(Collectors.toList());
    }

    public List<ReturnsDto> getReturnsByMemberId(UUID memberId) {
        List<Returns> returnsList = returnsRepository.findByMemberId(memberId);
        return returnsList.stream().map(returnsMapper::mapToDto).collect(Collectors.toList());
    }

    public ReturnsDto createReturns(ReturnsDto returnsDto) {
        Returns returns = returnsMapper.mapToEntity(returnsDto);
        Returns savedReturns = returnsRepository.save(returns);
        return returnsMapper.mapToDto(savedReturns);
    }
}
