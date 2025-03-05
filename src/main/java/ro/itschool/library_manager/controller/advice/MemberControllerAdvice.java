package ro.itschool.library_manager.controller.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.itschool.library_manager.exception.InvalidMemberException;

@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(InvalidMemberException.class)
    public ResponseEntity<ProblemDto> handleInvalidMemberException(InvalidMemberException ex) {
        return ResponseEntity.badRequest().body(
                new ProblemDto(ex.getMessage(), HttpStatus.BAD_REQUEST.toString())
        );
    }
}
