package ro.itschool.library_manager.exception;

public class InvaliPayloadException extends RuntimeException {

    public InvalidPayloadException(String message){
        super(message);
    }
}
