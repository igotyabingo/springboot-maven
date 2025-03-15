package kr.co.ordermanagement.domain.exception;

public class LackOfStockException extends RuntimeException {
    public LackOfStockException (String message) {
        super(message);
    }
}
