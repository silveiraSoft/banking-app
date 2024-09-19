package com.adalbertosn1982.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//public class BusinessException extends RuntimeException {
public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String message, Throwable e) {
        super(ErrorCodes.valueOf(message), e);
    }
    public BusinessException(ErrorCodes errorCodes) {
        super(errorCodes);
    }
    public BusinessException(ErrorCodes errorCodes, Throwable e) {
        super(errorCodes, e);
    }
    public BusinessException(ErrorCodes errorCodes, String additionalErrorMessage) {
        super(errorCodes, additionalErrorMessage);
    }
    public BusinessException(ErrorCodes errorCodes, String additionalErrorMessage, Throwable e) {
        super(errorCodes, additionalErrorMessage, e);
    }


}
