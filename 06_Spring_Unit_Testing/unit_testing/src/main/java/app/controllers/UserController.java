package app.controllers;


import app.entities.bindingModels.users.UserRegistrationModel;
import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller()
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping("/users/register")
    public String showRegisterPage(@ModelAttribute UserRegistrationModel userRegistrationModel)
    {
        return "users/user-register";
    }

    @PostMapping("/users/register")
    public String submitRegisterForm(@Valid @ModelAttribute UserRegistrationModel userRegistrationModel, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "users/user-register";
        } else
        {
            this.userService.register(userRegistrationModel);
            return "redirect:/users/login";
        }
    }

    @GetMapping("/users/login")
    public String showLoginPage(@RequestParam(required = false) String error, Model model, Principal principal)
    {
        if (error != null)
        {
            model.addAttribute("error", "Invalid Credentials");
        }
        try
        {
            principal.getName();
        } catch (NullPointerException e)
        {
            return "users/user-login";
        }
        return "redirect:/";
    }

    @GetMapping("/unauthorized")
    public String showUnauthorizedView()
    {
        return "errors/403";
    }


}
