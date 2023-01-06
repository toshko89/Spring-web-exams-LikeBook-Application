package com.example.likebook.repository;

import com.example.likebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findUserByUsername(String name);

    Optional<User> findUserByEmail(String email);

    User findUserById(long id);
}
