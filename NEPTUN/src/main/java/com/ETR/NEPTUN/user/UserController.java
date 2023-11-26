package com.ETR.NEPTUN.user;

import com.ETR.NEPTUN.addcourse.AddCourse;
import com.ETR.NEPTUN.addcourse.AddCourseService;
import com.ETR.NEPTUN.user.dto.RegisterUser;
import com.ETR.NEPTUN.user.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Controller
public class UserController {

    private final UserService userService;
    private final AddCourseService addCourseService;

    @Autowired
    public UserController(UserService userService, AddCourseService addCourseService) {
        this.userService = userService;
        this.addCourseService = addCourseService;
    }

    //Login page
    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        if (userService.isValidUser(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Hibás felhasználónév vagy jelszó");
            return "Login";
        }
    }


    @GetMapping("/register")
    public String registerPage() {
        return "Register";
    }
    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("doBorn") String doBorn,
                           @RequestParam("doBirth") LocalDate doBirth,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("status") String status,
                           @RequestParam("major") String major,
                           Model model) {
        // Ellenőrizze, hogy a felhasználónév foglalt-e
        if (userService.isUserExists(username)) {
            model.addAttribute("error", "A felhasználónév már foglalt!");
            return "Register";
        }

        // Ha nem foglalt, akkor végezze el a regisztrációt
        RegisterUser newUser = new RegisterUser(username, password, doBorn, doBirth, firstName, lastName, status, major);
        userService.registerUser(newUser);

        // Sikeres regisztráció esetén irányítsa át a felhasználót a bejelentkezési oldalra
        model.addAttribute("success", "Sikeres regisztráció!");
        return "Register";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("username", username);
            return "Dashboard";
        }
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            UserDTO user = userService.findByUsername(username);
            List<AddCourse> addedCourses = addCourseService.getCoursesForUser(username);
            model.addAttribute("user", user);
            model.addAttribute("addedCourses", addedCourses);
            return "Profile";
        }
    }
}
