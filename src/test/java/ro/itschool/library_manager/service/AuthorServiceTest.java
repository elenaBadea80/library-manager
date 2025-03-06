package ro.itschool.library_manager.service;


import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.itschool.library_manager.dto.AuthorDto;
import ro.itschool.library_manager.mapper.impl.AuthorMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.repository.AuthorRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    @Test
    @DisplayName("Test createAuthor - Success Case")
    void testCreateAuthor_Success() {
        // given
        AuthorDto authorDto = new AuthorDto(
                UUID.randomUUID(),
                "John Doe"
        );

        Author mappedAuthor = new Author();
        mappedAuthor.setId(UUID.randomUUID());
        mappedAuthor.setAuthorName("John Doe");

        when(authorMapper.mapToEntity(authorDto)).thenReturn(mappedAuthor);
        when(authorRepository.save(mappedAuthor)).thenReturn(mappedAuthor);

        // when
        authorService.createAuthor(authorDto);

        // then
        verify(authorMapper).mapToEntity(authorDto);
        verify(authorRepository).save(mappedAuthor);
        verifyNoMoreInteractions(authorMapper, authorRepository);
    }

    @Test
    @DisplayName("Test createAuthor - Mapper Throws Exception")
    void testCreateAuthor_MapperThrowsException() {
        // given
        AuthorDto authorDto = new AuthorDto(
                UUID.randomUUID(),
                "John Doe"
        );

        when(authorMapper.mapToEntity(authorDto)).thenThrow(new IllegalArgumentException("Mapping error"));

        // then
        assertThrows(IllegalArgumentException.class, () -> authorService.createAuthor(authorDto));

        verify(authorMapper).mapToEntity(authorDto);
        verifyNoInteractions(authorRepository);
    }
}