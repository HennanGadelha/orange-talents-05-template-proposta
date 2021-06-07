package com.proposta.config.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrosDeFormularioDto> handler(MethodArgumentNotValidException ex){

        List<ErrosDeFormularioDto> errosDeFormularioDtos = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrosDeFormularioDto error = new ErrosDeFormularioDto(e.getField(), mensagem);
            errosDeFormularioDtos.add(error);
        });

        return errosDeFormularioDtos;
    }

    @ExceptionHandler(ExistingProposalException.class)
    public ResponseEntity<?> existingProposalHandler(ExistingProposalException ex){

        return ResponseEntity.status(ex.getStatus()).body("Proposta já cadastrada");
    }

}
