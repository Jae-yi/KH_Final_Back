package com.kh.devs_server.dao;

import com.kh.devs_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);

    List<User> findByUserEmail(String userEmail);

    List<User> findByUserEmailAndPassword(String userEmail, String password);

    List<User> findByUserEmailAndPhone(String userEmail, String phone);

}

