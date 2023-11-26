package com.ETR.NEPTUN.addcourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddCourseRepository extends JpaRepository<AddCourse, Long> {
    @Query("SELECT ac FROM AddCourse ac WHERE ac.user.username = ?1")
    List<AddCourse> getCoursesForUser(String username);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AddCourse u WHERE u.user.username = ?1 AND u.course.id = ?2")
    boolean existsByUsernameAndId(String username, Long courseId);

    @Modifying
    @Query("DELETE FROM AddCourse u WHERE u.user.username = ?1 AND u.course.id = ?2")
    void deleteByIdAndUsername(String username, Long courseId);
}
