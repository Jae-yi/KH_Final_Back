package com.kh.devs_server.controller;
import com.kh.devs_server.dao.StudyRepository;
import com.kh.devs_server.dto.StudyDTO;
import com.kh.devs_server.entity.Study;
import com.kh.devs_server.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final StudyRepository studyRepository;

    @GetMapping("/studies")
    public ResponseEntity<List<Study>> studyList(){
        List<Study> list = studyService.getStudyList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/study/{studyId}")
    public ResponseEntity<Study> study(@PathVariable Long studyId) {
        Optional<Study> study = studyService.getStudy(studyId);
        return new ResponseEntity(study, HttpStatus.OK);
    }

    @PostMapping("/study/write")
    public ResponseEntity<StudyDTO> writeStudy(@RequestBody StudyDTO studyDTO) {
//        로그인 파트에서 세션으로 주면 받아올 예정
        boolean result = studyService.writeStudy(studyDTO);

        if(result){
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("study/{studyId}")
    public void deleteStudy(@PathVariable Long studyId) {
        studyRepository.deleteById(studyId);
    }
}
