package co.edu.unisabana.Gribu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler{

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundHandler(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RequestException.class)
    public ResponseEntity<String> requestExceptionHandler(RequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> unauthorizedExceptionHandler(UnauthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ExistingResourceException.class)
    public ResponseEntity<String> existingUserExceptionHandler(ExistingResourceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationExceptionHandler(AuthenticationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }



}
