package com.kh.devs_server.entity;

import com.kh.devs_server.constant.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(name = "profileImage")
    private String profileImage;

    // user-role enum type : user/admin 추가
    // default는 user로 하고, admin 계정은 미리 서버에 넣어두는걸로 하기
    @Column(name = "userRole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
}
