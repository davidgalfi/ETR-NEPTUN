package com.ETR.NEPTUN.room;

import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.exam.Exam;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Terem")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Kod")
    private Long id;

    @Column(name = "Cim")
    private String address;

    @Column(name = "Emelet")
    private Integer floor;

    @Column(name = "Ajto")
    private Integer door;

    @Column(name = "Jelleg")
    private String type;

    @Column(name = "Nev")
    private String name;

    @Column(name = "Ferohely")
    private Integer capacity;

    @OneToMany(mappedBy = "room")
    private Set<Course> course;

    @OneToMany(mappedBy = "room")
    private Set<Exam> exam;
}
