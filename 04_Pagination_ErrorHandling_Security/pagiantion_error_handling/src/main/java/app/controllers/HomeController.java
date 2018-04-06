package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String getHomeView()
    {
        System.out.println("sdadsa");
        return "home-view";
    }
}
