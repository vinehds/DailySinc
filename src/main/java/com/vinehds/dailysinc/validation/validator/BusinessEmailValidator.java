package com.vinehds.dailysinc.validation.validator;

import com.vinehds.dailysinc.service.exception.InvalidEmailException;
import com.vinehds.dailysinc.validation.annotation.BusinessEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BusinessEmailValidator implements ConstraintValidator<BusinessEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !email.endsWith("@supportweb.com.br")) {
            throw new InvalidEmailException("The email must be corporate (@supportweb.com.br)");
        }
        return true;
    }
}
