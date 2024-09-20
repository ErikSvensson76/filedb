package org.example.filedb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class FileDbControllerAdvice {

  public ExceptionResponse build(String message, HttpStatus status, WebRequest request) {
    return new ExceptionResponse(
        LocalDateTime.now(),
        status.value(),
        status.name(),
        message,
        request.getDescription(false),
        null
    );
  }

  @ExceptionHandler(AppResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ExceptionResponse> handleAppResourceNotFoundException(
      final AppResourceNotFoundException ex, final WebRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        build(ex.getMessage(), HttpStatus.NOT_FOUND, request)
    );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(
      final IllegalArgumentException ex, final WebRequest request){
    return ResponseEntity.badRequest().body(
        build(ex.getMessage(), HttpStatus.BAD_REQUEST, request)
    );
  }

}
