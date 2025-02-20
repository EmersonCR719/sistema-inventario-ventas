package com.code.gestion.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class VentaExceptions extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public VentaExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public VentaExceptions(String message) {
        super(message);
    }
}
