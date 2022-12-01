package com.kh.devs_server.service;

import com.kh.devs_server.constant.UserRole;
import com.kh.devs_server.entity.User;
import com.kh.devs_server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {
    // Repository와 연결
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //회원가입
    public boolean regUser(String userEmail, String userNickname, String password, String phone, String profileImage) {
        // User Entity와 연결
        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserNickname(userNickname);
        user.setPassword(password);
        user.setPhone(phone);
        user.setProfileImage(profileImage);
        user.setCreateDate(LocalDateTime.now());
        user.setUserRole(UserRole.USER);
        User rst = userRepository.save(user);
        log.warn(rst.toString());
        return true;
    }

    // 회원 조회
    public List<User> userSearch(String userEmail) {
        List<User> user = userRepository.findByUserEmail(userEmail);
        return user;
    }

    // 로그인 체크
    public List<User> loginCheck(String userId, String pwd) {
        List<User> memberList = userRepository.findByUserEmailAndPassword(userId, pwd);

        return memberList;
    }
}
