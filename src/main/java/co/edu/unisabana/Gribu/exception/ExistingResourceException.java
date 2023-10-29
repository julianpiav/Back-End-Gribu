package co.edu.unisabana.Gribu.exception;

public class ExistingResourceException extends RuntimeException{
    public ExistingResourceException(String message) {
        super(message);
    }
}
