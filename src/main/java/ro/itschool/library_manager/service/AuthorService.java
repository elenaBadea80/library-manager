package ro.itschool.library_manager.service;

import org.springframework.stereotype.Service;
import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.repository.AuthorRepository;

import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ObjectMapper<AuthorDto, Author> authorMapper;

    public AuthorService (AuthorRepository authorRepository,
                          ObjectMapper<AuthorDto, Author> authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public void createAuthor(AuthorDto authorDto) {
        Author author = authorMapper.mapToEntity(authorDto);

        authorRepository.save(author);
    }

//    public List<AuthorDto> getAuthorsByCategoryName(CategoryDto categoryDtoName) {
//        List<Author> authors = authorRepository.findAuthorsByCategoryName(categoryDtoName.getCategoryName());
//
//        return authors.stream()
//                .map(authorMapper::mapToDto)
//                .toList();
//    }

    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }
}
