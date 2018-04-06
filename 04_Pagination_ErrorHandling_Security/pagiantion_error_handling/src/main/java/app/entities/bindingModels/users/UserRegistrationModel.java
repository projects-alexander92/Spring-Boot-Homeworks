package app.entities.bindingModels.users;

import app.entities.bindingModels.annotations.PasswordMatch;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@PasswordMatch
public class UserRegistrationModel
{
    @NotBlank
    @Size(min = 5, max = 10, message = "Size must be between 5 and 10")
    private String username;
    @Size(min = 5, message = "min size 5")
    private String password;
    private String confirmPassword;

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getConfirmPassword()
    {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

}
