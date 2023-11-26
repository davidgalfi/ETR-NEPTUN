package com.ETR.NEPTUN.course;


import com.ETR.NEPTUN.course.dto.RegisterCourse;
import com.ETR.NEPTUN.room.Room;
import com.ETR.NEPTUN.room.RoomService;
import com.ETR.NEPTUN.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final RoomService roomService;

    @Autowired
    public CourseService(CourseRepository courseRepository, RoomService roomService) {
        this.courseRepository = courseRepository;
        this.roomService = roomService;
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

    @Transactional
    public void updateCourse(Long courseIdU,
                             String titleCU,
                             Integer capacityCU,
                             String typeCU,
                             String semesterCU,
                             Integer weeklyHoursCU,
                             Long roomIdCU) {
        Optional<Course> optionalCourse = courseRepository.findById(courseIdU);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            if(!titleCU.toString().equals("")){
                System.out.println(titleCU.equals("") + titleCU + "--------------------------------------------------------");
                course.setTitle(titleCU);
            }
            if(capacityCU.intValue() != 0) {
                course.setCapacity(capacityCU);
            }
            if(!typeCU.toString().equals("")){
                course.setType(typeCU);
            }
            if(!semesterCU.toString().equals("NoneChange")){
                course.setSemester(semesterCU);
            }
            if(weeklyHoursCU.intValue() != 0){
                course.setWeeklyHours(weeklyHoursCU);
            }
            if(roomIdCU.intValue() != -1){
                course.setRoom(roomService.findByRoomId(roomIdCU));
            }
        } else {
            throw new IllegalStateException("Course not found");
        }

    }
}
