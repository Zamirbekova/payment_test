package com.test.payment_test.valid;

import com.test.payment_test.modul.Users;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        Users user = (Users) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
