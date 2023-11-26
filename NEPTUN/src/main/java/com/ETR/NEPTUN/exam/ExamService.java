package com.ETR.NEPTUN.exam;

import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.exam.dto.RegisterExam;
import com.ETR.NEPTUN.room.Room;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public Exam findByExamId(Long examId) {
        return examRepository.findById(examId).orElse(null);
    }

    public List<Exam> getMyExams(String username) {
        return examRepository.getMyExams(username);
    }

    public boolean isTeacherOwnTheExam(String username, Long examId) {
        return examRepository.isTeacherOwnTheExam(username, examId);
    }

    public boolean existByIdAndRoomId(LocalDateTime dateTime, Long roomIdE) {
        return examRepository.existByIdAndRoomId(dateTime, roomIdE);
    }

    public void registerExam(RegisterExam newExam, Course course, Room room) {
        Exam exam = new Exam(
                newExam.type(),
                newExam.capacity(),
                newExam.dateTime(),
                course,
                room
        );
        examRepository.save(exam);
    }

    @Transactional
    public void deleteExam(Long examId) {
        examRepository.deleteById(examId);
    }
}
