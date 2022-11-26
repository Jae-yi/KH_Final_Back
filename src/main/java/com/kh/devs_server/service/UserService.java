package com.kh.devs_server.service;

import com.kh.devs_server.entity.User;
import com.kh.devs_server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean regUser(String userName, String userNickname, String password, String phone) {
        User user = new User();
        user.setUserName(userName);
        user.setUserNickname(userNickname);
        user.setPassword(password);
        user.setPhone(phone);
        user.setCreateDate(LocalDateTime.now());
        User rst = userRepository.save(user);
        log.warn(rst.toString());
        return true;
    }
}
