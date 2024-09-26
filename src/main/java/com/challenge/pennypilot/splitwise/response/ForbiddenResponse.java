package com.challenge.pennypilot.splitwise.response;

import org.springframework.http.HttpStatus;

public class ForbiddenResponse {
    private final int status;
    private final String message;

    public ForbiddenResponse() {
        this.status = 4;
        this.message = "You are not authorized to perform this action";
    }

    public ForbiddenResponse(String message) {
        this.status = 4;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
