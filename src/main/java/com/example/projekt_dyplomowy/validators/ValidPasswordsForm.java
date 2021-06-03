package com.example.projekt_dyplomowy.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPasswordsFormValidator.class)
public @interface ValidPasswordsForm {
    String message() default "{passwords.not.valid.error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
