package com.example.likebook.validation.annotations;

import com.example.likebook.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
