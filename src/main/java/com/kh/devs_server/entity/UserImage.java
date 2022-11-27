package com.kh.devs_server.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class UserImage {
    @Id
    @Column(name="userImg_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String file_name;

    @Column(nullable = false)
    private String file_path;

    @Column(nullable = false)
    private Long file_size;

}
