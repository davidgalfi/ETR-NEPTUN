package com.ETR.NEPTUN.addexam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddExamRepository extends JpaRepository<AddExam, Long> {

    @Query("SELECT ae FROM AddExam ae WHERE ae.user.username = ?1")
    public List<AddExam> getExamsForUser(String username);
}
