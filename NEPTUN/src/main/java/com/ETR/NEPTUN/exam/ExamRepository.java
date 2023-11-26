package com.ETR.NEPTUN.exam;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findById(Long examId);

    @Query("SELECT e FROM Exam e WHERE e.course.user.username = ?1")
    List<Exam> getMyExams(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Exam u WHERE u.course.user.username = ?1 AND u.id = ?2")
    boolean isTeacherOwnTheExam(String username, Long examId);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Exam u WHERE u.date = ?1 AND u.room.id = ?2")
    boolean existByIdAndRoomId(LocalDateTime dateTime, Long roomIdE);
}
