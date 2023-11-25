package com.ETR.NEPTUN.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
