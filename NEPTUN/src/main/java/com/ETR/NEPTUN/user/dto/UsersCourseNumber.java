package com.ETR.NEPTUN.user.dto;

public class UsersCourseNumber {
    private String username;
    private Long courseCount;

    public UsersCourseNumber() {
    }

    public UsersCourseNumber(String username, Long courseCount) {
        this.username = username;
        this.courseCount = courseCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Long courseCount) {
        this.courseCount = courseCount;
    }
}

