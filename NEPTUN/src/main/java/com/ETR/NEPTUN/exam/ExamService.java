package com.ETR.NEPTUN.exam;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }


    public List<Exam> getAllExam() {
        return examRepository.findAll();
    }
}
