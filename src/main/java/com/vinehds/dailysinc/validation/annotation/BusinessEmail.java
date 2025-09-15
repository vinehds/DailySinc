package com.vinehds.dailysinc.validation.annotation;

import com.vinehds.dailysinc.validation.validator.BusinessEmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BusinessEmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessEmail {

    String message() default "O email deve ser corporativo (@supportweb.com.br)";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};

}
