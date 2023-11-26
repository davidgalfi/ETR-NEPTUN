package com.ETR.NEPTUN.exam;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    @GetMapping("/exams")
    public String examsPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        } else {
            List<Exam> exams = examService.getAllExam();
            model.addAttribute("username", username);
            model.addAttribute("exams", exams);
            return "Exams";
        }
    }
}
