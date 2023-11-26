package com.ETR.NEPTUN.exam;


import com.ETR.NEPTUN.addexam.AddExam;
import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.room.Room;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private LocalDateTime date;

    @Column(name = "Letszam")
    private Integer studentsNumber;

    @ManyToOne
    @JoinColumn(name = "Kurzus_kod")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "Terem_kod")
    private Room room;

    @OneToMany(mappedBy = "exam")
    private Set<AddExam> addExam;

    public Exam() {
    }

    public Exam(Long id, String type, Integer capacity, LocalDateTime date, Course course, Room room) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
        this.date = date;
        this.studentsNumber = 0;
        this.course = course;
        this.room = room;
    }

    public Exam(String type,
                Integer capacity,
                LocalDateTime date,
                Course course,
                Room room) {
        this.type = type;
        this.capacity = capacity;
        this.date = date;
        this.studentsNumber = 0;
        this.course = course;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<AddExam> getAddExam() {
        return addExam;
    }

    public void setAddExam(Set<AddExam> addExam) {
        this.addExam = addExam;
    }

    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }
}
