package com.ETR.NEPTUN.user;

import com.ETR.NEPTUN.user.dto.UsersCourseNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT new com.ETR.NEPTUN.user.dto.UsersCourseNumber(ac.user.username, COUNT(ac.course.id)) " +
            "FROM AddCourse ac " +
            "WHERE ac.user.status = 'Hallgató'" +
            "GROUP BY ac.user.username " +
            "ORDER BY ac.user.username")
    List<UsersCourseNumber> getAllUsersCourseNumber();

    @Query("SELECT u FROM User u WHERE u.status = 'Oktató' ORDER BY u.doBirth ASC")
    List<User> getAllInstructorsOrderByBirthDate();
}
