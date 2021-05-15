package com.example.projekt_dyplomowy.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProjectNameUniquenessValidator.class)
public @interface UniqueProjectName {
    String message() default "{name.unique.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
