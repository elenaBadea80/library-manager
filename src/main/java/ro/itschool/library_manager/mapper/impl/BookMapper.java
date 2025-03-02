package ro.itschool.library_manager.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.library_manager.dto.BookDto;
import ro.itschool.library_manager.mapper.ObjectMapper;
import ro.itschool.library_manager.persistence.entity.Author;
import ro.itschool.library_manager.persistence.entity.Book;
import ro.itschool.library_manager.persistence.entity.Member;
import ro.itschool.library_manager.persistence.repository.AuthorRepository;
import ro.itschool.library_manager.persistence.repository.MemberRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class BookMapper implements ObjectMapper<BookDto, Book> {

    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;

    public BookMapper(AuthorRepository authorRepository,
                      MemberRepository memberRepository) {
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public Book mapToEntity(BookDto bookDto) {
        Book book = new Book();

        Set<Author> authors = new HashSet<>();
        bookDto.getAuthors()
                .forEach(a -> {
                    Optional<Author> authorById = authorRepository.findById(a);
                    authors.add(authorById.orElseThrow());
                });
        book.setAuthors(authors);
        Set<Member> members = new HashSet<>();
        bookDto.getMembers()
                .forEach(m -> {
                    Optional<Member> memberById = memberRepository.findById(m);
                    members.add(memberById.orElseThrow());
                });
        book.setMembers(members);
        book.setTitle(bookDto.getTitle());
        book.setCategoryBook(bookDto.getCategoryBook());
        book.setYear(bookDto.getYear());

        return book;
    }

    @Override
    public BookDto mapToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getCategoryBook(),
                book.getAuthors().stream()
                        .map(Author::getId)
                        .toList(),
                book.getMembers().stream()
                        .map(Member::getId)
                        .toList(),
                book.getYear()
        );
    }

}