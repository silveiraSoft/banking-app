package com.adalbertosn1982.banking.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Problem {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="America/Bogota")
    //private LocalDateTime dataHora;
    //private String mensagem;

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private Integer code;
    /*
    private String instance;
    private String path;
    private String method;
    private String trace;
    private String locale;
    private String environment;
    private String version;
    private String system;
    private String component;
    private String support;
    private String cause;
    */
}
