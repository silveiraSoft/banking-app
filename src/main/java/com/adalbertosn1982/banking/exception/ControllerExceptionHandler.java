package com.adalbertosn1982.banking.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = NoSuchElementException.class)
    public ProblemDetail onNoSuchElement(NoSuchElementException e) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        problemDetail.setInstance(new URI(request.getRequestURI()));
        problemDetail.setType(URI.create("http://api.employees.com/errors/not-found"));
        problemDetail.setTitle("Element Not Found");
        return problemDetail;
    }


    @ExceptionHandler(value = BusinessException.class)
    public ProblemDetail onBusinessException(BusinessException e) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        problemDetail.setInstance(new URI(request.getRequestURI()));
        problemDetail.setType(URI.create("http://api.employees.com/errors/business"));
        problemDetail.setTitle("Business Error");
        return problemDetail;
    }

}
