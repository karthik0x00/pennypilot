package com.challenge.pennypilot.splitwise.response;

import org.springframework.http.HttpStatus;

public class SuccessResponse implements Response {
    private int status;
    private String message;

    public SuccessResponse() {
        status = 0;
        message = "Success";
    }

    public SuccessResponse(String message) {
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
