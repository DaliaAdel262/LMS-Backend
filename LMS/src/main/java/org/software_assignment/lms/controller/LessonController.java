package org.software_assignment.lms.controller;

import org.software_assignment.lms.entity.LessonEntity;
import org.software_assignment.lms.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
    @PostMapping(value = "/{lessonID}/generate-otp")
    public ResponseEntity<String> generateOTP(@PathVariable int lessonID) {
        try {
            String otp = lessonService.generateOTP(lessonID);
            return ResponseEntity.ok("OTP generated successfully: " + otp);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{lessonID}")
    public ResponseEntity<LessonEntity> displayLesson(@PathVariable int lessonID) {
        try {
            LessonEntity lesson = lessonService.displayLesson(lessonID);
            return ResponseEntity.ok(lesson);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



}
