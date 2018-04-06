package app.services.impl;

import app.entities.bindingModels.users.UserRegistrationModel;
import app.entities.orm.Role;
import app.entities.orm.User;
import app.parser.ModelParser;
import app.repositories.RoleRepository;
import app.repositories.UserRepository;
import app.services.interfaces.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest
{
    private static final long DEFAULT_ROLE_ID = 1;
    private static final String INVALID_USERNAME = "!#!#Aasas";
    private static final String VALID_USERNAME = "sasho";
    private static final String VALID_PASSWORD = "12345";
    private UserRegistrationModel userRegistrationModel;
    private User user;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelParser modelParser;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;

    @Before
    public void initObjects()
    {
        Role role = new Role();
        role.setAuthority("ROLE_USER");

        this.userRegistrationModel = new UserRegistrationModel();
        this.userRegistrationModel.setUsername(VALID_USERNAME);
        this.userRegistrationModel.setPassword(VALID_PASSWORD);
        this.userRegistrationModel.setConfirmPassword("12345");

        this.user = this.modelParser.parse(this.userRegistrationModel, User.class);

        Mockito.when(roleRepository.findOne(DEFAULT_ROLE_ID)).thenReturn(role);
        Mockito.when(this.userRepository.findByUsername(VALID_USERNAME)).thenReturn(this.user);
    }

    @Test
    public void testIfRegisterCallsRepositoryMethodSave()
    {
        this.userService.register(this.userRegistrationModel);
        Mockito.verify(this.userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testIfLoadUserByUsernameTWithInvalidUsername()
    {
        this.userService.loadUserByUsername(INVALID_USERNAME);
        Mockito.verify(this.userRepository, Mockito.times(1)).findByUsername(INVALID_USERNAME);
    }

    @Test
    public void testIfLoadUserByUsernameWithValidUsername()
    {
        UserDetails userDetails = this.userService.loadUserByUsername(VALID_USERNAME);
        Assert.assertTrue("username mismatch", userDetails.getUsername().equals(VALID_USERNAME));
        Assert.assertTrue("username mismatch", userDetails.getPassword().equals(VALID_PASSWORD));
        System.out.println(userDetails);
    }
}