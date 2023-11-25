package com.ETR.NEPTUN.user.dto;

import java.time.LocalDate;

public record UserDTO(String username,
                      String doBorn,
                      LocalDate doBirth,
                      String firstName,
                      String lastName,
                      String status,
                      String major) {
}
