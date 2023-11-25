package com.ETR.NEPTUN.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "Register";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        return "Dashboard";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        return "Profile";
    }
}
