package com.challenge.pennypilot.splitwise.controlleradvice;

import com.challenge.pennypilot.splitwise.exception.AuthorizationException;
import com.challenge.pennypilot.splitwise.exception.InvalidDataProvidedException;
import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.response.ForbiddenResponse;
import com.challenge.pennypilot.splitwise.response.InvalidDataProvidedResponse;
import com.challenge.pennypilot.splitwise.response.MethodNotSupportedResponse;
import com.challenge.pennypilot.splitwise.response.NotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler({ NoResourceFoundException.class, NoHandlerFoundException.class })
    public ResponseEntity<NotFoundResponse> handleNoHandlerFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new NotFoundResponse());
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<NotFoundResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new NotFoundResponse(e.getMessage()));
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<MethodNotSupportedResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).contentType(MediaType.APPLICATION_JSON).body(new MethodNotSupportedResponse(e.getMethod()));
    }

    @ExceptionHandler({ InvalidDataProvidedException.class })
    public ResponseEntity<InvalidDataProvidedResponse> handleInvalidDataProvidedException(InvalidDataProvidedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(new InvalidDataProvidedResponse(e.getMessage()));
    }

    @ExceptionHandler({ AuthorizationException.class })
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(new ForbiddenResponse(e.getMessage()));
    }
}
