package com.ETR.NEPTUN.addexam;

import com.ETR.NEPTUN.exam.Exam;
import com.ETR.NEPTUN.exam.ExamRepository;
import com.ETR.NEPTUN.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddExamService {

    private final AddExamRepository addExamRepository;
    private final ExamRepository examRepository;

    @Autowired
    public AddExamService(AddExamRepository addExamRepository, ExamRepository examRepository) {
        this.addExamRepository = addExamRepository;
        this.examRepository = examRepository;
    }

    public List<AddExam> getExamsForUser(String username) {
        return addExamRepository.getExamsForUser(username);
    }

    public boolean existByUsername(String username, Long examId) {
        return addExamRepository.existByUsernameAndId(username, examId);
    }

    public boolean isExamFilled(Long examId) {
        Exam exam = examRepository.findById(examId).get();
        if(exam.getCapacity() <= exam.getStudentsNumber()){
            return true;
        }
        return false;
    }

    public void saveExam(Exam exam, User user) {
        AddExam addExam = new AddExam(user, exam);
        addExamRepository.save(addExam);
    }

    @Transactional
    public void deleteAddExam(Long examId, String username) {
        addExamRepository.deleteByIdAndUsername(examId,username);
    }

    @Transactional
    public void deleteAllAddCourseById(Long examId) {
        addExamRepository.deleteAllByExamId(examId);
    }

}
