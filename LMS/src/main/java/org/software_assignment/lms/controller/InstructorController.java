package org.software_assignment.lms.controller;

import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.QuizEntity;
import org.software_assignment.lms.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController()
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping("/api/add/course")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public ResponseEntity<String> addCourses(@RequestBody CourseEntity course) {
        try {
            instructorService.addCourse(course);
            return ResponseEntity.ok("Successfully added course");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @DeleteMapping("/api/delete/std/course/{courseID}/{stdID}")
    ResponseEntity<String> deleteCourses(@PathVariable("courseID") String courseID , @PathVariable("stdID") int stdID)
    {
        try {
            instructorService.removeStudent(courseID, stdID);
            return ResponseEntity.ok("Successfully deleted student");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/add/quiz")
    ResponseEntity<String> addQuizzes(@RequestBody QuizEntity quiz) {
        try {
            instructorService.addQuiz(quiz);
            return ResponseEntity.ok("Successfully added quiz");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
