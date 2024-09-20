package com.challenge.pennypilot.splitwise.response;

import org.springframework.http.HttpStatus;

public class CreationSuccessResponse implements Response {
    private final int status;
    private final String message;

    public CreationSuccessResponse() {
        this.status = 0;
        this.message = "Created successfully";
    }

    public CreationSuccessResponse(String message) {
        this.status = HttpStatus.CREATED.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
