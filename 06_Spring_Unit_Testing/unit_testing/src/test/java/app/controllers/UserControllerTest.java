package app.controllers;

import app.services.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest
{
    private static final String URL_TEMPLATE = "/users/register";
    private static final String EXPECTED_VIEW_NAME = "redirect:/users/login";
    private static final String EXPECTED_URL = "/users/login";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void submitRegisterFormWithValidData() throws Exception
    {
        mockMvc
                .perform(post(URL_TEMPLATE)
                        .param("username", "sasho12")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(EXPECTED_VIEW_NAME))
                .andExpect(redirectedUrl(EXPECTED_URL))
                .andExpect(model().hasNoErrors());
    }

    @Test
    public void submitRegisterFormWithInValidData() throws Exception
    {
        mockMvc
                .perform(post(URL_TEMPLATE)
                        .param("username", "sasho12")
                        .param("password", "1234561")
                        .param("confirmPassword", "123456")
                )
                .andExpect(status().is2xxSuccessful());
    }
}