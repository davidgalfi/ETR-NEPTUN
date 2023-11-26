package com.ETR.NEPTUN.addexam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddExamRepository extends JpaRepository<AddExam, Long> {

    @Query("SELECT ae FROM AddExam ae WHERE ae.user.username = ?1")
    public List<AddExam> getExamsForUser(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AddExam u WHERE u.user.username = ?1 AND u.exam.id = ?2")
    boolean existByUsernameAndId(String username, Long examId);

    @Modifying
    @Query("DELETE FROM AddExam u WHERE u.user.username = ?2 AND u.exam.id = ?1")
    void deleteByIdAndUsername(Long examId, String username);
}
