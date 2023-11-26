package com.ETR.NEPTUN.user;

import com.ETR.NEPTUN.user.dto.RegisterUser;
import com.ETR.NEPTUN.user.dto.UserDTO;
import com.ETR.NEPTUN.user.dto.UserDTOMapper;
import com.ETR.NEPTUN.user.dto.UsersCourseNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }


    public boolean isValidUser(String username, String password) {
        if(username == null || password == null) {
            return false;
        }
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userDTOMapper::apply)
                .orElse(null);
    }

    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public void registerUser(RegisterUser newUser) {
        User user = new User(
                newUser.username(),
                newUser.password(),
                newUser.doBorn(),
                newUser.doBirth(),
                newUser.firstName(),
                newUser.lastName(),
                newUser.status(),
                newUser.major()
        );
        userRepository.save(user);
    }

    public User findWholeByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<UsersCourseNumber> getAllUserCourseNumber() {
        return userRepository.getAllUsersCourseNumber();
    }

    public List<User> getAllInstructorsOrderByBirthDate(){
        return userRepository.getAllInstructorsOrderByBirthDate();
    }
}
