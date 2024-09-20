package com.challenge.pennypilot.splitwise.response;


public class NotFoundResponse implements Response {
    private final int status;
    private final String message;

    public NotFoundResponse() {
        this.status = -1;
        this.message = "Requested resource does not exist";
    }

    public NotFoundResponse(String message) {
        this.status = -1;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
