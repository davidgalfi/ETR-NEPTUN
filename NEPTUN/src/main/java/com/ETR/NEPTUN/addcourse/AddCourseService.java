package com.ETR.NEPTUN.addcourse;

import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.course.CourseRepository;
import com.ETR.NEPTUN.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddCourseService {

    private final AddCourseRepository addCourseRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public AddCourseService(AddCourseRepository addCourseRepository, CourseRepository courseRepository) {
        this.addCourseRepository = addCourseRepository;
        this.courseRepository = courseRepository;
    }

    public List<AddCourse> getCoursesForUser(String username) {
        return addCourseRepository.getCoursesForUser(username);
    }

    public boolean existByUsername(String username, Long courseId) {
        return addCourseRepository.existsByUsernameAndId(username, courseId);
    }

    public boolean isCourseFilled(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        if(course.getCapacity() <= course.getStudentsNumber()){
            return true;
        }
        return false;
    }

    public void saveCourse(Course course, User user) {
        AddCourse addCourse = new AddCourse(
            user,
            course
        );
        addCourseRepository.save(addCourse);
    }

    @Transactional
    public void deleteAddCourse(Long courseId, String username) {
        addCourseRepository.deleteByIdAndUsername(username,courseId);
    }

    public AddCourse findByCourseId(Long courseId) {
        return addCourseRepository.findById(courseId).get();
    }

    @Transactional
    public void deleteAllAddCourseById(Long courseId) {
        addCourseRepository.deleteAllByCourseId(courseId);
    }
}
