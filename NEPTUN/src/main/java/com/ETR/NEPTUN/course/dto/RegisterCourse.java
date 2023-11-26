package com.ETR.NEPTUN.course.dto;

public record RegisterCourse(
        String title,
        Integer capacity,
        String type,
        String semester,
        Integer weeklyHours
) {
}
