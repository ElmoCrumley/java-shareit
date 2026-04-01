package ru.practicum.shareit.exception;

public class BadDataBody extends RuntimeException {
    public BadDataBody(String message) {
        super(message);
    }
}
