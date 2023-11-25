package com.ETR.NEPTUN.user.dto;

import com.ETR.NEPTUN.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User input) {
        return new UserDTO(
            input.getUsername(),
            input.getDoBorn(),
            input.getDoBirth(),
            input.getFirstName(),
            input.getLastName(),
            input.getStatus(),
            input.getMajor()
        );
    }
}
