package com.ETR.NEPTUN.exam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamController {

    @GetMapping("/exams")
    public String examsPage(Model model) {
        return "Exams";
    }
}
