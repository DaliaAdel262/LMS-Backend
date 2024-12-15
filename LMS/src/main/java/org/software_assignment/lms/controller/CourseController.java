package org.software_assignment.lms.controller;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.software_assignment.lms.entity.*;
import java.util.*;


@RestController
@RequestMapping(value = "/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/greetings")
    public String greetings(){
        return "Hello World";
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
    @PostMapping("/{courseId}/questions/add")
    public ResponseEntity<String> addQuestionToCourse(
            @PathVariable String courseId,
            @RequestParam String question,
            @RequestParam String answer) {
        String result = courseService.addQuestionToCourse(courseId, question, answer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
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

//    @PostMapping
//    public ResponseEntity<CourseEntity> addCourse(
//            @RequestBody CourseEntity course, // validation must be added, ask entity to add validation
//            @RequestHeader("role") String userRole // Assume role is passed in request headers
//            // we can use spring security access control for this too
//    ) {
//        // Verify the user's role in the controller
//        if (!"Admin".equalsIgnoreCase(userRole)) {
//            return ResponseEntity.status(403).body(null); // Forbidden for non-Admin users
//        }

//        // Call the service layer to handle course creation
//        CourseEntity createdCourse = courseService.addCourse(course);
//        return ResponseEntity.ok(createdCourse);
//    }


}
