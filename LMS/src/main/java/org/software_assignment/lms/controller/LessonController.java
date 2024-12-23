package org.software_assignment.lms.controller;

import java.util.NoSuchElementException;

import org.apache.catalina.connector.Response;
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

    //add lesson
//    @PostMapping("/courses/{courseId}/lessons")
//    public ResponseEntity<String> addLesson(
//            @PathVariable String courseId,
//            @RequestParam int lessonId,
//            @RequestParam String title,
//            @RequestParam String content,
//            @RequestParam int duration) {
//        try {
//            lessonService.addLessonToCourse(lessonId, courseId, title, content, duration);
//            return ResponseEntity.ok("Lesson added successfully.");
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }


    @PostMapping
    public ResponseEntity<String> addLesson(@RequestBody LessonEntity lessonEntity) {
        try {
            LessonEntity savedLesson = lessonService.addLesson(lessonEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Lesson added successfully with ID: " + savedLesson.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the lesson");
        }
    }


}
