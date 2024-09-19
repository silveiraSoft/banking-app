package com.adalbertosn1982.banking.controller.client.exception;

import com.adalbertosn1982.banking.exception.Problem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

import java.io.Serial;
@Getter
@Slf4j
public class ClientApiException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private Problem problem;

    public ClientApiException(String message) {
        super(message);
    }
    public ClientApiException(String message, RestClientResponseException cause) {
        super(message, cause);
        System.out.println("Cause: " + cause.getResponseBodyAsString());
        //cause.printStackTrace();
        deserializeProblem(cause);
    }
  /*
    public ClientApiException(String message, Throwable cause) {
        super(message, cause);
    }
    public ClientApiException(Throwable cause) {
        super(cause);
    }
    public ClientApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ClientApiException() {
        super();
    }*/
    private void deserializeProblem(RestClientResponseException cause) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            this.problem = objectMapper.readValue(cause.getResponseBodyAsString(), Problem.class);
        } catch (JsonProcessingException e) {
            //throw new RuntimeException(e);
            //e.printStackTrace();
            log.warn("Error deserializing the response in a Problem");
        }
    }


}
