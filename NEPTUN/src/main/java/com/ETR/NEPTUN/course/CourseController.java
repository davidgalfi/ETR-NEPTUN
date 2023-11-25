package com.ETR.NEPTUN.course;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/courses")
    public String coursesPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("username", username);
            return "Courses";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute("username") == null){
            return "redirect:/login";
        }
        session.removeAttribute("username");
        return "redirect:/login";
    }
}
