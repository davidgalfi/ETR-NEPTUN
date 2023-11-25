package com.ETR.NEPTUN.course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/courses")
    public String coursesPage(Model model) {
        return "Courses";
    }
}
