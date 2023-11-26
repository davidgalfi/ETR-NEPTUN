package com.ETR.NEPTUN.course;


import com.ETR.NEPTUN.course.dto.RegisterCourse;
import com.ETR.NEPTUN.room.Room;
import com.ETR.NEPTUN.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course findByCourseId(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public List<Course> getMyCourses(String username) {
        return courseRepository.findByUser_Username(username);
    }

    public boolean isTeacherOwnTheCourse(String username, Long courseId) {
        return courseRepository.isTeacherOwnTheCourse(username, courseId);
    }

    public void registerCourse(RegisterCourse newCourse, Room room, User user) {
        Course course = new Course(
                newCourse.title(),
                newCourse.capacity(),
                newCourse.type(),
                newCourse.semester(),
                newCourse.weeklyHours(),
                room,
                user);
        courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
