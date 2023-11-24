package com.ETR.NEPTUN.course;

import com.ETR.NEPTUN.addcourse.AddCourse;
import com.ETR.NEPTUN.exam.Exam;
import com.ETR.NEPTUN.room.Room;
import com.ETR.NEPTUN.user.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Kurzus")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Kod")
    private Long id;

    @Column(name = "Cim")
    private String title;

    @Column(name = "Ferohely")
    private Integer capacity;

    @Column(name = "Jelleg")
    private String type;

    @Column(name = "Szemeszter")
    private String semester;

    @Column(name = "Heti_oraszam")
    private int weeklyHours;

    @ManyToOne
    @JoinColumn(name = "Terem_kod")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "Felhasznalo_felhasznalonev")
    private User user;

    @OneToMany(mappedBy = "course")
    private Set<Exam> exam;

    @OneToMany(mappedBy = "course")
    private Set<AddCourse> addCourses;
}
