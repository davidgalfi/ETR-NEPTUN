package com.ETR.NEPTUN.addcourse;

import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AddCourseRepository extends JpaRepository<AddCourse, Long> {
    @Query("SELECT ac FROM AddCourse ac WHERE ac.user.username = ?1")
    List<AddCourse> getCoursesForUser(String username);
}
