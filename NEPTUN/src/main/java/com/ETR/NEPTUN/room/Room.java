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

    public Room() {
    }

    public Room(Long id, String address, Integer floor, Integer door, String type, String name, Integer capacity) {
        this.id = id;
        this.address = address;
        this.floor = floor;
        this.door = door;
        this.type = type;
        this.name = name;
        this.capacity = capacity;
    }

    public Room(String address, Integer floor, Integer door, String type, String name, Integer capacity) {
        this.address = address;
        this.floor = floor;
        this.door = door;
        this.type = type;
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getDoor() {
        return door;
    }

    public void setDoor(Integer door) {
        this.door = door;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }

    public Set<Exam> getExam() {
        return exam;
    }

    public void setExam(Set<Exam> exam) {
        this.exam = exam;
    }
}
