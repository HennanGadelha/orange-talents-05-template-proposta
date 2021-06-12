package com.proposta.config.validacoes.isbase64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, String> {


    @Override
    public void initialize(IsBase64 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null) return false;

        try{
            String  valorDecodificado = new String(Base64.getDecoder().decode(value));
            String valorCodificado = Base64.getEncoder().encodeToString(valorDecodificado.getBytes());
            return value.equals(valorCodificado);

        }catch (Exception ex){
            return  false;
        }

    }
}
