package com.example.likebook.validation;

import com.example.likebook.service.UserService;
import com.example.likebook.validation.annotations.UniqueUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName,String> {

    private final UserService userService;

    @Autowired
    public UniqueUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.userNameIsTaken(value) == null;
    }
}
