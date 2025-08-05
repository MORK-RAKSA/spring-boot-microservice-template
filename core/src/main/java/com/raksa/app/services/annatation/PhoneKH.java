package com.raksa.app.services.annatation;

import com.raksa.app.services.validations.CambodianPhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CambodianPhoneValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneKH {

    String message() default "Invalid Phone Format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
