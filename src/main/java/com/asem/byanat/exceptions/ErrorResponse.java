package com.asem.byanat.exceptions;

import org.springframework.http.HttpStatusCode;

public class ErrorResponse {
    private int statusCode;
    private HttpStatusCode status;
    private String message;
    public ErrorResponse() {
    }
    public ErrorResponse(int statusCode,
                         HttpStatusCode status,
                         String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
