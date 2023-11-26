package com.ETR.NEPTUN.addexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddExamService {

    private final AddExamRepository addExamRepository;

    @Autowired
    public AddExamService(AddExamRepository addExamRepository) {
        this.addExamRepository = addExamRepository;
    }

    public List<AddExam> getExamsForUser(String username) {
        return addExamRepository.getExamsForUser(username);
    }
}
