package app.services.interfaces;

import app.entities.bindingModels.users.UserRegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService
{
    void register(UserRegistrationModel userRegistrationModel);

    public default void print()
    {
        System.out.println("sdaadssdads");
    }
}
