package com.kh.devs_server.service;

import com.kh.devs_server.dao.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional  // 실행 후 db 롤백
@RequiredArgsConstructor
public class StudyServiceTest {

    private final StudyService studyService;
    @Autowired
    private StudyRepository studyRepository;


}
