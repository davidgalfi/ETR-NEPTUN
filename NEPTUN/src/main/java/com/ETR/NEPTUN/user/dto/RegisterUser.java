package com.ETR.NEPTUN.user.dto;

import java.time.LocalDate;
import java.util.Date;

public record RegisterUser(
        String username,
        String password,
        String doBorn,
        LocalDate doBirth,
        String firstName,
        String lastName,
        String status,
        String major
) {
}
