package com.kh.devs_server.service;

import com.kh.devs_server.entity.User;
import com.kh.devs_server.entity.UserImage;
import com.kh.devs_server.repository.UserImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class UserImageService{
    private UserImageRepository userImageRepository;

    public UserImageService(UserImageRepository userImageRepository){
        this.userImageRepository=userImageRepository;
    }

    public boolean uploadImage(User userId, String uid, String file_name, String file_path, Long file_size) {

        UserImage userImage = new UserImage();
        userImage.setUuid(uid);
        userImage.setUser(userId);
        userImage.setFile_name(file_name);
        userImage.setFile_path(file_path);
        userImage.setFile_size(file_size);
        UserImage rst = userImageRepository.save(userImage);

        return true;
    }

}
