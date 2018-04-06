package app.entities.bindingModels.annotations;

import app.entities.bindingModels.users.UserRegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegistrationModel>
{
    @Override
    public void initialize(PasswordMatch constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(UserRegistrationModel userRegistrationModel, ConstraintValidatorContext context)
    {
        if (userRegistrationModel.getPassword() == null || userRegistrationModel.getConfirmPassword() == null)
        {
            return false;
        }
        return userRegistrationModel.getPassword().equals(userRegistrationModel.getConfirmPassword());
    }
}
