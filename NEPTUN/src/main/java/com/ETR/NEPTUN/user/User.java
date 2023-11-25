package com.ETR.NEPTUN.user;

import com.ETR.NEPTUN.addcourse.AddCourse;
import com.ETR.NEPTUN.addexam.AddExam;
import com.ETR.NEPTUN.course.Course;
import com.ETR.NEPTUN.exam.Exam;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Felhasznalo")
public class User {

    @Id
    @Column(name = "Felhasznalo_nev")
    private String username;

    @Column(name = "Jelszo")
    private String password;

    @Column(name = "Szuletesi_hely")
    private String doBorn;

    @Column(name = "Szuletesi_ido")
    private LocalDate doBirth;

    @Column(name = "Keresztnev")
    private String firstName;

    @Column(name = "Vezeteknev")
    private String lastName;

    @Column(name = "Statusz")
    private String status;

    @Column(name = "Szak")
    private String major;

    @OneToMany(mappedBy = "user")
    private Set<Course> course;

    @OneToMany(mappedBy = "user")
    private Set<AddExam> addExam;

    @OneToMany(mappedBy = "user")
    private Set<AddCourse> addCourse;

    public User() {
    }

    public User(String username,
                String password,
                String doBorn,
                LocalDate doBirth,
                String firstName,
                String lastName,
                String status,
                String major) {
        this.username = username;
        this.password = password;
        this.doBorn = doBorn;
        this.doBirth = doBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.major = major;
    }

    public User(String username,
                String password,
                String doBorn,
                LocalDate doBirth,
                String firstName,
                String lastName,
                String status,
                String major,
                Set<Course> course,
                Set<AddExam> addExam,
                Set<AddCourse> addCourse) {
        this.username = username;
        this.password = password;
        this.doBorn = doBorn;
        this.doBirth = doBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.major = major;
        this.course = course;
        this.addExam = addExam;
        this.addCourse = addCourse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDoBorn() {
        return doBorn;
    }

    public void setDoBorn(String doBorn) {
        this.doBorn = doBorn;
    }

    public LocalDate getDoBirth() {
        return doBirth;
    }

    public void setDoBirth(LocalDate doBirth) {
        this.doBirth = doBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }

    public Set<AddExam> getAddExam() {
        return addExam;
    }

    public void setAddExam(Set<AddExam> addExam) {
        this.addExam = addExam;
    }

    public Set<AddCourse> getAddCourse() {
        return addCourse;
    }

    public void setAddCourse(Set<AddCourse> addCourse) {
        this.addCourse = addCourse;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", doBorn='" + doBorn + '\'' +
                ", doBirth=" + doBirth +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", major='" + major + '\'' +
                ", course=" + course +
                ", addExam=" + addExam +
                ", addCourse=" + addCourse +
                '}';
    }
}
