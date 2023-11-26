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

    @Column(name = "Letszam")
    private Integer studentsNumber;

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

    public Course() {
    }


    public Course(Long id, String title, Integer capacity, String type, String semester, int weeklyHours, Room room, User user) {
        this.id = id;
        this.title = title;
        this.capacity = capacity;
        this.type = type;
        this.semester = semester;
        this.weeklyHours = weeklyHours;
        this.studentsNumber = 0;
        this.room = room;
        this.user = user;
    }

    public Course(String title, Integer capacity, String type, String semester, int weeklyHours, Room room, User user) {
        this.title = title;
        this.capacity = capacity;
        this.type = type;
        this.semester = semester;
        this.weeklyHours = weeklyHours;
        this.studentsNumber = 0;
        this.room = room;
        this.user = user;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Exam> getExam() {
        return exam;
    }

    public void setExam(Set<Exam> exam) {
        this.exam = exam;
    }

    public Set<AddCourse> getAddCourses() {
        return addCourses;
    }

    public void setAddCourses(Set<AddCourse> addCourses) {
        this.addCourses = addCourses;
    }

    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }
}
