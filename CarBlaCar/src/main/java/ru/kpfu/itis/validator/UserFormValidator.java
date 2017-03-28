package ru.kpfu.itis.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.forms.UserForm;

/**
 * Created by розалия on 27.03.2017.
 */
@Component
public class UserFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm userForm = (UserForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password mustn't be empty");

        if (!(userForm.getPassword()).equals(userForm.getPasswordConfirmation())) {
            errors.rejectValue("passwordConfirmation", "passwordConfirmation.passwordDon'tMatch", "Passwords don't match");
        }
        if (!EmailValidator.getInstance().isValid(userForm.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email address is not valid");
        }

    }

}