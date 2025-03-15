package kr.co.ordermanagement.presentation;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.exception.InvalidStateException;
import kr.co.ordermanagement.domain.exception.LackOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException (EntityNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LackOfStockException.class)
    public ResponseEntity<String> handleLackOfStockException (LackOfStockException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<String> handleInvalidStateException (InvalidStateException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
