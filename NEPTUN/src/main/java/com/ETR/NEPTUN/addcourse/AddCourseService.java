package com.ETR.NEPTUN.addcourse;

import com.ETR.NEPTUN.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddCourseService {

    private final AddCourseRepository addCourseRepository;

    @Autowired
    public AddCourseService(AddCourseRepository addCourseRepository) {
        this.addCourseRepository = addCourseRepository;
    }

    public List<AddCourse> getCoursesForUser(String username) {
        return addCourseRepository.getCoursesForUser(username);
    }
}
