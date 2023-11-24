package com.ETR.NEPTUN.exam;


import com.ETR.NEPTUN.addexam.AddExam;
import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.room.Room;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Vizsga")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Kod")
    private Long id;

    @Column(name = "Jelleg")
    private String type;

    @Column(name = "Ferohely")
    private Integer capacity;

    @Column(name = "Idopont")
    private String date;

    @ManyToOne
    @JoinColumn(name = "Kurzus_kod")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "Terem_kod")
    private Room room;

    @OneToMany(mappedBy = "exam")
    private Set<AddExam> addExam;
}
