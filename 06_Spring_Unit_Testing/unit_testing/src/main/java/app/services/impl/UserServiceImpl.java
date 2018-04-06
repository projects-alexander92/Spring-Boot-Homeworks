package app.services.impl;

import app.entities.bindingModels.users.UserRegistrationModel;
import app.entities.orm.Role;
import app.entities.orm.User;
import app.parser.ModelParser;
import app.repositories.RoleRepository;
import app.repositories.UserRepository;
import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    private static final long DEFAULT_ROLE_ID = 1;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void register(UserRegistrationModel userRegistrationModel)
    {
        User user = this.modelParser.parse(userRegistrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(userRegistrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        Role defaultRole = this.roleRepository.findOne(DEFAULT_ROLE_ID);
        user.getAuthorities().add(defaultRole);

        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.userRepository.findByUsername(username);
        if (user != null)
        {
            return user;
        }
        throw new UsernameNotFoundException("Invalid Credentials");
    }
}
