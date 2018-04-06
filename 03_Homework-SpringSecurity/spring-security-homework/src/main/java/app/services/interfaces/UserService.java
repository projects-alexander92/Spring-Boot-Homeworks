package app.services.interfaces;

import app.entities.dto.bindingModels.users.UserRegistrationModel;
import app.entities.dto.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{
    void register(UserRegistrationModel userRegistrationModel);

    List<UserViewModel> getAll();
}
