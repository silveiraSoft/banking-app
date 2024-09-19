package com.adalbertosn1982.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends BaseException{
    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String mensagem) {
        super(mensagem);
    }

    public EntityNotFoundException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }

    public EntityNotFoundException(ErrorCodes errorCodes) {
        super(errorCodes.getDescription(), errorCodes.getCode(), errorCodes.name());
    }

    public EntityNotFoundException(ErrorCodes errorCodes, Throwable e) {
        super(errorCodes.getDescription(), errorCodes.getCode(), errorCodes.name(), e);
    }

}
