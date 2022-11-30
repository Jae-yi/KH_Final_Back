package com.kh.devs_server.controller;

import com.kh.devs_server.constant.UserRole;
import com.kh.devs_server.entity.User;
import com.kh.devs_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class UserController {
    // Service 로직 연결
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> userRegister(@RequestBody Map<String, String> regData) {
        String getUserEmail = regData.get("userEmail");
        String getUserNickname = regData.get("userNickname");
        String getPassword = regData.get("password");
        String getPhone = regData.get("phone");
        String getProfileImage = regData.get("profileImage");
        boolean result = userService.regUser(getUserEmail, getUserNickname, getPassword, getPhone, getProfileImage, UserRole.USER);
        if (result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<User> memberLogin(@RequestBody Map<String, String> loginData) {

        String userEmail = loginData.get("userEmail");
        String password = loginData.get("password");
        User result = userService.loginCheck(userEmail, password);
        if (result.getUserId() != null) {

            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

}
