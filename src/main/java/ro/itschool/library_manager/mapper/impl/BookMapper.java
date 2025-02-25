package ro.itschool.library_manager.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Book;


@Component
public class BookMapper implements ObjectMapper<BookDto, Book> {

    @Override
    public Book mapToEntity(BookDto bookDto) {
        Book book = new Book();

        book.setTitle(bookDto.getTitle());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(bookDto.getAuthor());
        book.setYear(bookDto.getYear());

        return book;
    }

    @Override
    public BookDto mapToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                book.getAuthor(),
                book.getYear()
        );
    }

}
