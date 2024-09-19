package com.adalbertosn1982.banking.exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    ACCOUNT_INSUFFICIENT_FUND(1000, "Insufficient found in Account", "account-insificient-fund", "Insufficient found in Account"),
    ACCOUNT_NOT_FOUND(1001, "Account not found","",""),
    ENTITY_NOT_FOUND(1002, "Entity not found","/entity-not-found","Entity not found"),
    ACCOUNT_ALREADY_EXISTS(1003, "Account already exists","",""),

    UNKNOWN_ERROR(1999, "Unknown error", "unknow-error", "Unknown error"),
    ;

    private final int code;
    private final String description;
    private final String uri;
    private final String title;

    ErrorCodes(int code, String description, String path, String title) {
        this.code = code;
        this.description = description;
        this.uri = "https://adalbertosn.com"+path;
        this.title = title;
    }

    @Override
    public String toString() {
        return code + ": " + description + ": " + uri + ": " + title;
    }
}
