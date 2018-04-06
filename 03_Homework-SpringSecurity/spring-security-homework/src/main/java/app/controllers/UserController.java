package app.controllers;

import app.entities.dto.bindingModels.enums.Role;
import app.entities.dto.bindingModels.users.UserRegistrationModel;
import app.entities.dto.viewModels.UserViewModel;
import app.entities.orm.enums.Mutation;
import app.services.interfaces.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @ModelAttribute(name = "roles")
    public List<String> getRoles()
    {
        List<String> roles = new ArrayList<>();
        Role[] values = Role.values();
        for (Role value : values)
        {
            roles.add(value.toString());
        }
        return roles;

    }

    @GetMapping("users/register")
    public String showRegisterPage(@ModelAttribute UserRegistrationModel userRegistrationModel)
    {
        return "users/user-register";
    }

    @PostMapping("users/register")
    public String submitRegisterForm(@Valid @ModelAttribute UserRegistrationModel userRegistrationModel, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "users/user-register";
        } else
        {
            this.userService.register(userRegistrationModel);
            return "redirect:/uses/login";
        }
    }

    @GetMapping("users/login")
    public String showLoginPage(@RequestParam(required = false) String error, Model model)
    {
        if (error != null)
        {
            model.addAttribute("error", "Invalid Credentials");
        }
        System.out.println(error);
        return "users/user-login";
    }

    @GetMapping("/unauthorized")
    public String showUnauthorizedView()
    {
        return "errors/403";
    }

    @GetMapping("users/all")
    public String showAllUsers(Model model)
    {
        List<UserViewModel> users = this.userService.getAll();
        model.addAttribute("users", users);
        return "users/user-table-view";
    }

}
