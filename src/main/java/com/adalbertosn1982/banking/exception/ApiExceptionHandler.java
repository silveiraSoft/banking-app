package com.adalbertosn1982.banking.exception;

//import jakarta.persistence.EntityNotFoundException;
import com.sun.net.httpserver.Headers;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> treatEntityNotFoundException(
            EntityNotFoundException e, WebRequest request) {
        /*
        Problem problema = Problem.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .code(e.getCode()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problema);
        */
        HttpStatus status = HttpStatus.BAD_REQUEST;
        /*
        Object body = Problem.builder()
                //.dataHora(LocalDateTime.now())
                //.mensagem(status.toString()) //status.getReasonPhrase()
                .type("https://adalbertosn.com/entidy-not-found")
                .title("Entity not found")
                .status(status.value())
                .detail(e.getMessage())
                .code(e.getCode()).build();

         */
        Object body =createProblemBuilder(status,e)
                .type("https://adalbertosn.com/entidy-not-found")
                .title("Entity not found")
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> treatBusinessException(BusinessException e, WebRequest request) {
        /*
        Problem problema = Problem.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .code(e.getCode()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problema);
        */
        /*
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        */
        HttpStatus status = HttpStatus.BAD_REQUEST;
        /*
        Object body = Problem.builder()
                //.dataHora(LocalDateTime.now())
                //.mensagem(status.toString()) //status.getReasonPhrase()
                .type("https://adalbertosn.com/business-exception")
                .title("Business Exception")
                .status(status.value())
                .detail(e.getMessage())
                .code(e.getCode()).build();

         */
        Problem body = createProblemBuilder(status, e)
                //.dataHora(LocalDateTime.now())
                //.mensagem(status.toString()) //status.getReasonPhrase()
                .type("https://adalbertosn.com/business-exception")
                .title("Business Exception").build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /*
    @Primary
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> treatNoResourceFoundException(
            NoResourceFoundException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Object body =createProblemBuilder(status,e)
                .type("https://adalbertosn.com/no-resource-found-exception")
                .title("Entity not found")
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
    }
     */

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (!(ex instanceof BaseException)) {
            body = Problem.builder()
                    //.dataHora(LocalDateTime.now())
                    //.mensagem(status.toString()) //status.getReasonPhrase()
                    .title(status.toString())
                    .status(status.value())
                    //.code(ex1.getCode())
                    .detail(ex.getMessage())
                    .build();;
        } else if(body == null) {
            BaseException ex1 = (BaseException) ex;
            body = Problem.builder()
                    //.dataHora(LocalDateTime.now())
                    //.mensagem(status.toString()) //status.getReasonPhrase()
                    .title(status.toString())
                    .status(status.value())
                    .code(ex1.getCode())
                    .detail(ex1.getMessage())
                    .build();
        } else if (body instanceof String) {
            BaseException ex1 = (BaseException) ex;
            body = Problem.builder()
                    //.dataHora(LocalDateTime.now())
                    //.mensagem((String) body)
                    .status(status.value())
                    .title((String) body)
                    .code(ex1.getCode())
                    .detail(ex1.getMessage())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, Exception e) {
        BaseException ex1 = (BaseException) e;
        return Problem.builder()
                .status(status.value())
                .code(ex1.getCode())
                .detail(ex1.getMessage());
    }

    /*
    @ExceptionHandler(MechanismNotFoundException.class)
    public ResponseEntity<Problem> treatMechanismNotFoundException(BusinessException e) {
        Problem problema = Problem.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .code(e.getCode()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problema);
    }
     */

}
