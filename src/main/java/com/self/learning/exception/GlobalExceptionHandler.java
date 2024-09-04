package com.self.learning.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle user not found exception response entity.
   *
   * @param userNotFoundException the user not found exception
   * @return the response entity
   */
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleUserNotFoundException(
      UserNotFoundException userNotFoundException) {
    Map<String, String> response = new HashMap<>();
    response.put("error", "User not found");
    response.put("message", userNotFoundException.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  /**
   * Handle global exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGlobalException(Exception exception) {
    Map<String, String> response = new HashMap<>();
    response.put("error", "Internal server error");
    response.put("message", exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handle argument not valid exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, List<String>>> handleArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    List<String> errors = exception.getBindingResult().getFieldErrors().stream()
        .map(FieldError::getDefaultMessage).collect(
            Collectors.toList());
    return new ResponseEntity<>(mapErrors(errors), HttpStatus.BAD_REQUEST);
  }

  private Map<String, List<String>> mapErrors(List<String> errors) {
    Map<String, List<String>> response = new HashMap<>();
    response.put("errors", errors);
    return response;
  }

}
