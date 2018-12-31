package com.example.todotasklist3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(TaskNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Task ID : '" + ex.getMessage()+ "' Not Found",
                request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubjectException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(SubjectException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Subject is required",
                request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StatusException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(StatusException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Status, Whether it is Pending or Done",
                request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    /*protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                "Validation Failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }*/
}
