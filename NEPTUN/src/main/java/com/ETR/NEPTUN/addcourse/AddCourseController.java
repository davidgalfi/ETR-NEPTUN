package com.ETR.NEPTUN.addcourse;

import com.ETR.NEPTUN.course.Course;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AddCourseController {

    private final AddCourseService addCourseService;

    @Autowired
    public AddCourseController(AddCourseService addCourseService) {
        this.addCourseService = addCourseService;
    }

    @GetMapping("/addedcourses")
    public String addedcoursesPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            List<AddCourse> addedCourses = addCourseService.getCoursesForUser(username);
            model.addAttribute("addedCourses", addedCourses);
            return "Profile";
        }
    }
}
