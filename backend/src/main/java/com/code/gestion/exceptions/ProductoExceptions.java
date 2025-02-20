package com.code.gestion.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProductoExceptions extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public ProductoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ProductoExceptions(String message) {
        super(message);
        this.message = message;
    }

}
