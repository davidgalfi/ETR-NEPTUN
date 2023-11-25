package com.ETR.NEPTUN.user.dto;

import com.ETR.NEPTUN.addcourse.AddCourse;
import com.ETR.NEPTUN.addexam.AddExam;

import java.time.LocalDate;
import java.util.Set;

public record UserDTO(String username,
                      String doBorn,
                      LocalDate doBirth,
                      String firstName,
                      String lastName,
                      String status,
                      String major,
                      Set<AddExam> addExam,
                      Set<AddCourse> addCourse) {
}
