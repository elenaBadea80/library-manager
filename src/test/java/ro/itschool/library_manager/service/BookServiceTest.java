package ro.itschool.library_manager.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.mapper.impl.BookMapper;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.repository.BookRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Test create book")
    void testCreateBook() {

        // given
        BookDto bookDto = new BookDto(
                UUID.randomUUID(),
                "Title",
                "Category",
                List.of(UUID.randomUUID()), // authors
                List.of(UUID.randomUUID()), // members
                2023
        );

        Book mappedBook = new Book();
        mappedBook.setId(UUID.randomUUID());
        mappedBook.setTitle("Title");
        mappedBook.setCategoryBook("Category");
        mappedBook.setYear(2023);

        when(bookMapper.mapToEntity(bookDto)).thenReturn(mappedBook);
        when(bookRepository.save(mappedBook)).thenReturn(mappedBook);

        // when
        bookService.createBook(bookDto);

        // then
        verify(bookMapper).mapToEntity(bookDto);
        verify(bookRepository).save(mappedBook);

    }

    @Test
    @DisplayName("Test createBook - Mapper Throws Exception")
    void testCreateBook_MapperThrowsException() {
        // given
        BookDto bookDto = new BookDto(
                UUID.randomUUID(),
                "Title",
                "Category",
                List.of(UUID.randomUUID()), // authors
                List.of(UUID.randomUUID()), // members
                2023
        );

        when(bookMapper.mapToEntity(bookDto)).thenThrow(new IllegalArgumentException("Mapping error"));

        // then
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(bookDto));

        verify(bookMapper).mapToEntity(bookDto);
        verifyNoInteractions(bookRepository);
    }
}
