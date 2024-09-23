package com.challenge.pennypilot.splitwise.response;


public class InvalidDataProvidedResponse implements Response {
    private String message = "Invalid Data Provided";

    public InvalidDataProvidedResponse() {}

    public InvalidDataProvidedResponse(String message) {
        this.message = message;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
