package com.ETR.NEPTUN.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findById(Long courseId);

    @Query("SELECT c FROM Course c WHERE c.user.username =?1")
    List<Course> findByUser_Username(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Course u WHERE u.user.username = ?1 AND u.id = ?2")
    boolean isTeacherOwnTheCourse(String username, Long courseId);


}
