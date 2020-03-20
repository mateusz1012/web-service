package com.mateuszs.WebService.jpa.services;

import com.mateuszs.WebService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByFirstName(String firstName);

    Optional<User> deleteUserById(Long id);
}

