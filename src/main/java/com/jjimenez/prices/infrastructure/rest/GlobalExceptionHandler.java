package com.jjimenez.prices.infrastructure.rest;

import com.jjimenez.prices.domain.exception.BrandNotFoundException;
import com.jjimenez.prices.domain.exception.PriceNotFoundException;
import com.jjimenez.prices.infrastructure.rest.dto.ErrorResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({PriceNotFoundException.class, BrandNotFoundException.class})
  public ResponseEntity<ErrorResponse> handlePriceNotFound(RuntimeException ex) {
    ErrorResponse error = new ErrorResponse(
        LocalDateTime.now(),
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        ex.getMessage()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(
        HttpStatus.BAD_REQUEST,
        "Invalid parameter type in the request"
    );
    problem.setProperty("invalid_parameter", ex.getName());
    problem.setProperty("expected_type",
        ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "Unknown");
    problem.setProperty("provided_value", ex.getValue());

    return problem;
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ProblemDetail handleMissingParameter(MissingServletRequestParameterException ex) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(
        HttpStatus.BAD_REQUEST,
        "Required parameter is missing in the request"
    );
    problem.setProperty("missing_parameter", ex.getParameterName());
    problem.setProperty("expected_type", ex.getParameterType());

    return problem;
  }

}
