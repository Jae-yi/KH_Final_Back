package com.kh.devs_server.service;

import com.kh.devs_server.entity.User;
import com.kh.devs_server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean regUser(String userEmail, String userNickname, String password, String phone) {
        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserNickname(userNickname);
        user.setPassword(password);
        user.setPhone(phone);
        user.setCreateDate(LocalDateTime.now());
        User rst = userRepository.save(user);
        log.warn(rst.toString());
        return true;
    }

    // 사용자 조회
    public List<User> userSearch(String userEmail) {
        List<User> user = userRepository.findByUserEmail(userEmail);
        return user;
    }

    public User loginCheck(String userId, String pwd) {
        List<User> memberList = userRepository.findByUserEmailAndPassword(userId, pwd);
        User info = memberList.get(0);

        return info;
    }
}
