package org.software_assignment.lms.controller;

import java.util.Arrays;
import java.util.List;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/courses")
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

    //    @Autowired
//    private CourseService courseService;
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCourse(
//            @PathVariable String id,
//            @RequestHeader("role") String userRole
//    ) {
//        // Verify the user's role in the controller
//        if (!"Admin".equalsIgnoreCase(userRole)) {
//            return ResponseEntity.status(403).body(null); // Forbidden for non-Admin users
//        }
//        try {
//            courseService.deleteCourseById(id);
//            return ResponseEntity.ok("Course deleted successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found: " + e.getMessage());
//        }
//    }


}
