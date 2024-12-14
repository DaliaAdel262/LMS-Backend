package org.software_assignment.lms.controller;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

//    @Autowired
//    private CourseService courseService;
//
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
//
//        // Call the service layer to handle course creation
//        CourseEntity createdCourse = courseService.addCourse(course);
//        return ResponseEntity.ok(createdCourse);
//    }
}
