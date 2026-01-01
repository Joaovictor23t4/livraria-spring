package com.livraria.exceptions;

import com.livraria.exceptions.category.CategoryNotFoundException;
import com.livraria.exceptions.publisher.PublisherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> genericException(MethodArgumentNotValidException ex) {
        ApiError apiError = ApiError.builder().timestamp(LocalDateTime.now()).http_status(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).status("GENERIC_ERROR").errors(List.of(ex.getMessage())).build();


        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({
            CategoryNotFoundException.class,
            PublisherNotFoundException.class
    })
    public ResponseEntity<ApiError> notFoundException(RuntimeException ex) {
        ApiError apiError = ApiError.builder().timestamp(LocalDateTime.now()).http_status(HttpStatus.NOT_FOUND.name()).code(HttpStatus.NOT_FOUND.value()).status("NOT_FOUND").errors(List.of(ex.getMessage())).build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> argumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorsList = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());

        ApiError apiError = ApiError.builder().timestamp(LocalDateTime.now()).http_status(HttpStatus.BAD_REQUEST.name()).code(HttpStatus.BAD_REQUEST.value()).status("INVALID_FIELD").errors(errorsList).build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ApiError> objectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        ApiError apiError = ApiError.builder().timestamp(LocalDateTime.now()).http_status(HttpStatus.NOT_FOUND.name()).code(HttpStatus.NOT_FOUND.value()).status("OPTIMIST_LOCKING_FAILURE").errors(List.of(ex.getMessage())).build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
