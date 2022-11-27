package com.kh.devs_server.controller;

import com.kh.devs_server.entity.User;
import com.kh.devs_server.service.UserImageService;
import com.kh.devs_server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    // Service 로직 연결
    private UserService userService;
    private UserImageService userImageService;
    public UserController(UserService userService, UserImageService userImageService) {
        this.userService = userService;
        this.userImageService = userImageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> userRegister(@RequestBody Map<String, String> regData) {
        String getUserEmail = regData.get("userEmail");
        String getUserNickname = regData.get("userNickname");
        String getPassword = regData.get("password");
        String getPhone = regData.get("phone");
        boolean result = userService.regUser(getUserEmail, getUserNickname, getPassword, getPhone);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/imageUpload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String, Object>> imageUpload(HttpServletRequest request,
            @RequestPart(value="file", required=false) MultipartFile[] files,
            @RequestPart(value = "userEmail", required = false)String userEmail) {

        boolean result = false;

        try {
            UUID uid = UUID.randomUUID();
            File targetFile = new File("C:/FinalProject/KH_Final_Back/src/main/resources/profileImage/" + uid.toString());

            String originFileName = files[0].getOriginalFilename();
            Long file_size = files[0].getSize();
            List<User> user = userService.userSearch(userEmail);
            result = userImageService.uploadImage(user.get(0), uid.toString(), originFileName, targetFile.toString(), file_size);

            files[0].transferTo(targetFile);
        } catch (IOException e) {
            System.out.println(e);
        }

        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/login")
    public ResponseEntity<User> memberLogin(@RequestBody Map<String, String> loginData) {

        String userEmail = loginData.get("userEmail");
        String password = loginData.get("password");
        User result = userService.loginCheck(userEmail, password);
        if(result.getUserId() != null) {

            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

}
