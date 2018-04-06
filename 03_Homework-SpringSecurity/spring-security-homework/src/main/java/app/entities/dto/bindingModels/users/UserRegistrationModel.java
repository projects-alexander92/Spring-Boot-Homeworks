package app.entities.dto.bindingModels.users;

import app.entities.dto.bindingModels.annotations.PasswordMatch;
import app.entities.dto.bindingModels.enums.Role;
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
    private Role role;

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

    public Role getRole()
    {
        return this.role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }
}
