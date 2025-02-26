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


@Service
public class ReturnsService {

    private final ReturnsRepository returnsRepository;
    private final ReturnsMapper returnsMapper;

    public ReturnsService(ReturnsRepository returnsRepository, ReturnsMapper returnsMapper) {
        this.returnsRepository = returnsRepository;
        this.returnsMapper = returnsMapper;
    }

    public Returns saveReturn(ReturnsDto returnsDto) {
        Returns returns = returnsMapper.mapToEntity(returnsDto);
        return returnsRepository.save(returns);
    }

    public Returns updateReturn(UUID id, ReturnsDto returnsDto) {
        Returns returns = returnsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Returns not found"));
        returns.setBook(returnsDto.getBook());
        returns.setMember(returnsDto.getMember());
        return returnsRepository.save(returns);
    }

    public List<Returns> getAllReturns() {
         return returnsRepository.findAll();
    }

    public Returns getReturnById(UUID id) {
        return  returnsRepository.findById(id).orElseThrow();
    }

}
