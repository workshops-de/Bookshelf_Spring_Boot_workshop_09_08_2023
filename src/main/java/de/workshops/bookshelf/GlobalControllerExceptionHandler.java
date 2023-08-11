package de.workshops.bookshelf;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.time.Instant;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraintViolationException(ConstraintViolationException e) {

            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
            problemDetail.setTitle("Violation Exception");
            problemDetail.setType(URI.create("http://localhost:8080/book_exception.html"));
            problemDetail.setProperty("timestamp", Instant.now());

            return problemDetail;
    }
}
