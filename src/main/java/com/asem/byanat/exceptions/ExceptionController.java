package com.asem.byanat.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpServletRequest req, HttpClientErrorException exception) {

        String msg = exception.getStatusCode().value() + " - Something wrong with the client side";

        return setErrorResponse(exception, msg);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleHttpServerErrorException(HttpServletRequest req, HttpServerErrorException exception) {
        String msg = exception.getStatusCode().value() + " - Something wrong with the server side";
        return setErrorResponse(exception, msg);
    }
    @ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<ErrorResponse> handleUnknownHttpStatusCodeException(HttpServletRequest req, UnknownHttpStatusCodeException exception) {
        String msg = exception.getStatusCode().value() + " - Something wrong.";
        return setErrorResponse(exception, msg);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(HttpServletRequest req, HttpClientErrorException.BadRequest exception) {
        String msg = exception.getStatusCode().value() + " - Something wrong.";
        return setErrorResponse(exception, msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException exception) {
        String msg = exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatusCode.valueOf(400));
        errorResponse.setMessage(msg);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    private ResponseEntity<ErrorResponse> setErrorResponse(RestClientResponseException exception, String message) {
        String error = exception.getStatusText() + ": " + message;
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getStatusCode().value(),
                exception.getStatusCode(),
                error);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }




}