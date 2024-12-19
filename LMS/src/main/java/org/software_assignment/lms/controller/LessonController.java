package org.software_assignment.lms.controller;

import java.util.NoSuchElementException;

import org.apache.catalina.connector.Response;
import org.software_assignment.lms.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "api/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    
    @GetMapping(value = "/{lesson_id}/courses/{course_id}/otp")
    public ResponseEntity<String> retrieveOTP(@RequestParam int studentID, @PathVariable int lessonID, @PathVariable String courseID){
        try{
            String otp = lessonService.getOTP(studentID, lessonID, courseID);
            return ResponseEntity.ok(otp);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(SecurityException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}