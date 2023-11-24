package com.ETR.NEPTUN.addcourse;

import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "KurzusFelvetel")
public class AddCourse {
    @Id
    @ManyToOne
    @JoinColumn(name = "Felhasznalo_Felhasznalonev")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Kurzus_kod")
    private Course course;
}
