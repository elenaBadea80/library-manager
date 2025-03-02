package ro.itschool.library_manager.exception;

public class InvalidPayloadException extends RuntimeException {

    public InvalidPayloadException(String message){
        super(message);
    }
}
