package com.kh.devs_server.repository;

import com.kh.devs_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserEmail(String userEmail);
    List<User> findByUserEmailAndPassword(String userEmail, String password);

}
