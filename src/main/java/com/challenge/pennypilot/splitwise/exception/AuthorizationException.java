package com.challenge.pennypilot.splitwise.exception;

public class AuthorizationException extends Exception {
    public AuthorizationException() {
        super("You are not authorized to perform this operation");
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
