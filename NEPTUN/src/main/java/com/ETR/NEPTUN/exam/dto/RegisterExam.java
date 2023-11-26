package com.ETR.NEPTUN.exam.dto;

import java.time.LocalDateTime;

public record RegisterExam(
        String type,
        Integer capacity,
        LocalDateTime dateTime
) {
}
