package me.woosuyeon.shorten.url.service.domain;

public class ExistingKeyException extends RuntimeException {
    public ExistingKeyException(String message) {
        super(message);
    }
}
