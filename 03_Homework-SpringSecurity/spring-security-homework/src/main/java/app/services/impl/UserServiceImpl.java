package app.services.impl;

import app.entities.dto.bindingModels.users.UserRegistrationModel;
import app.entities.dto.viewModels.UserViewModel;
import app.entities.orm.Role;
import app.entities.orm.User;
import app.parser.ModelParser;
import app.repositories.RoleRepository;
import app.repositories.UserRepository;
import app.security.UserRoles;
import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
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
        Role role = null;
        switch (userRegistrationModel.getRole().toString())
        {
            case "CHEMIST":
                role = this.roleRepository.findOne(UserRoles.CHEMIST_ROLE_ID);
                break;
            case "MEDIC":
                role = this.roleRepository.findOne(UserRoles.MEDIC_ROLE_ID);
                break;
            default:
                this.roleRepository.findOne(UserRoles.MEDIC_ROLE_ID);
                break;
        }
        User user = this.modelParser.parse(userRegistrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(userRegistrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.getAuthorities().add(role);
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

    @Override
    public List<UserViewModel> getAll()
    {
        List<UserViewModel> users = this.userRepository.getAllBy().stream().map(e ->
        {
            UserViewModel user = this.modelParser.parse(e, UserViewModel.class);
            user.setIsEnabled(e.isEnabled());
            return user;
        }).collect(Collectors.toList());
        return users;
    }
}
