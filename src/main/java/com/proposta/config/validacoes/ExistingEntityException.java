package com.proposta.config.validacoes;

import org.springframework.http.HttpStatus;


public class ExistingEntityException extends Throwable {

    private HttpStatus  status;
    private String message;


    public ExistingEntityException(HttpStatus  status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
