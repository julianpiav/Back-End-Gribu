package co.edu.unisabana.Gribu.Exceptions;

public class ExistingUserException extends RuntimeException{
    public ExistingUserException(String message) {
        super(message);
    }
}
