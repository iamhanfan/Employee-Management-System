package com.ems.app.sys.repository;

import com.ems.app.sys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Method to find a user by their email for the login process
    Optional<User> findByEmail(String email);
}