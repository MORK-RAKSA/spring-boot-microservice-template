package com.raksa.app.services.validations;

import com.raksa.app.services.annatation.PhoneKH;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CambodianPhoneValidator implements ConstraintValidator<PhoneKH, String> {

    private static final String KH_PHONE_PATTERN = "^(?:\\+855|0)[1-9][0-9]{7,8}$";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if(phone == null){
            return true;
        }else
            return phone.matches(KH_PHONE_PATTERN);
    }
}
