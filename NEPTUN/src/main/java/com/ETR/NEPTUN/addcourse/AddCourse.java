package com.ETR.NEPTUN.addcourse;

import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "kurzus_felvetel")
public class AddCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Felhasznalo_Felhasznalonev")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Kurzus_kod")
    private Course course;

    public AddCourse() {
    }

    public AddCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
