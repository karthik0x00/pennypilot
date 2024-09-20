package com.challenge.pennypilot.splitwise.response;


public class MethodNotSupportedResponse implements Response {
    int status;
    String message;

    public MethodNotSupportedResponse() {
        this.status = 2;
    }

    public MethodNotSupportedResponse(String method) {
        this();
        this.message = method + " Method not Supported for this endpoint";
    }
    @Override
    public int getStatus() {
        return 2;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
