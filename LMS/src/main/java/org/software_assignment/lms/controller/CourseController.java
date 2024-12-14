package org.software_assignment.lms.controller;
import org.software_assignment.lms.entity.CourseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.software_assignment.lms.service.CourseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/")
    public String greetings(){
        return "Hello World";

    }
    @GetMapping(value = "/courses")
    public List<CourseEntity>getAllCourses() {
        return courseService.findAll();
    }
    //delete student from course
    @DeleteMapping(value = "/{courseId}/students/{studentId}")
    public ResponseEntity<String> deleteStudentFromCourse(@PathVariable String  courseId,@PathVariable int studentId){

        boolean success=courseService.deleteStudent(courseId, studentId);
        if (success) {
            return ResponseEntity.ok("Student removed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course or student not found.");
        }
    }


}
