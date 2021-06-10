package com.proposta.config.validacoes;

import org.springframework.http.HttpStatus;


public class ExistingProposalException extends Throwable {

    private HttpStatus  status;
    private String message;


    public ExistingProposalException(HttpStatus  status, String message) {
        super(message);
        this.status = HttpStatus.UNPROCESSABLE_ENTITY;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
