package com.proposta.config.validacoes.valueofenum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {

    Class<? extends Enum<?>> enumClass();
    String message() default "Deve ser um enum válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
