package org.software_assignment.lms.controller;
import org.software_assignment.lms.entity.*;
import org.springframework.http.ResponseEntity;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;s

@RestController
@RequestMapping(value = "/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/greetings")
    public String greetings(){
        return "Hello World";
    }

    
    @PostMapping(value = "/")
   public ResponseEntity<CourseEntity> addCourse(
        @RequestParam  String id,
        @RequestParam String title,
        @RequestParam String description,
        @RequestParam int instructorId
   ) {
       CourseEntity createdCourse = courseService.addCourse(id,title,description,instructorId);
       return ResponseEntity.ok(createdCourse);
   }

    @GetMapping(value = "/")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable String id
    ) {
        try {
            courseService.deleteCourseById(id);
            return ResponseEntity.ok("Course deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found: " + e.getMessage());
        }
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteCourse(
    //         @PathVariable String id,
    //         @RequestHeader("role") String userRole
    // ) {
    //     // Verify the user's role in the controller
    //     if (!"Admin".equalsIgnoreCase(userRole)) {
    //         return ResponseEntity.status(403).body(null); // Forbidden for non-Admin users
    //     }
    //     try {
    //         courseService.deleteCourseById(id);
    //         return ResponseEntity.ok("Course deleted successfully!");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found: " + e.getMessage());
    //     }
    // }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable String id) {
        try {
            CourseEntity course = courseService.getCourseDetails(id);
            return ResponseEntity.ok(course);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Display all students enrolled in a course
    @GetMapping(value = "/{courseId}/students")
    public List<Student> getStudentsFromCourse(@PathVariable String courseId) {
        return courseService.getStudentsByCourseId(courseId);
    }
    
    //add question to question bank
    @PostMapping("/{courseId}/questions/add")
    public ResponseEntity<String> addQuestionToCourse(
            @PathVariable String courseId,
            @RequestParam String question,
            @RequestParam String answer) {
        String result = courseService.addQuestionToCourse(courseId, question, answer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}

