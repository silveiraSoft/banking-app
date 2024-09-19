package com.adalbertosn1982.banking.exception;

import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

@Getter
@ToString
public class BaseException extends RuntimeException /* IOException*/ {
    private final Integer code;
    private final String identification;
    private final String message;

    public BaseException(final String message) {
        this(message, 0, null, null);
    }

    public BaseException(final String message, Throwable cause) {
        this(message, null, null, cause);
    }

    public BaseException(ErrorCodes errorCodes){
        this(errorCodes.getDescription(), errorCodes.getCode(), errorCodes.name());
    }

    public BaseException(ErrorCodes errorCodes, Throwable e){
        this(errorCodes.getDescription(), errorCodes.getCode(), errorCodes.name(), e);
    }

    public BaseException(ErrorCodes errorCodes, String additionalErrorMessage){
        this(errorCodes.getDescription() + " (" + additionalErrorMessage + ")", errorCodes.getCode(), errorCodes.name());
    }

    public BaseException(ErrorCodes errorCodes, String additionalErrorMessage, Throwable cause){
        this(errorCodes.getDescription() + " (" + additionalErrorMessage + ")", errorCodes.getCode(), errorCodes.name(), cause);
    }

    public BaseException(final String message, final Integer code) {
        this(message, code, null);
    }

    public BaseException(final String message, final Integer code, final String identification) {
        this(message, code, identification, null);
    }

    public BaseException(final String message, final Integer code, final String identification, final Throwable throwable) {
        super(message, throwable);
        this.message = message;
        this.code = code;
        this.identification = identification;
    }
}
