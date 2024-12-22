package org.software_assignment.lms.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.CourseRepository;
import org.software_assignment.lms.repository.QuizRepository;
import org.software_assignment.lms.repository.UserRepository;
import org.software_assignment.lms.service.CourseService;
import org.software_assignment.lms.service.LessonService;
import org.software_assignment.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseService courseService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LessonService lessonService;
    @Autowired
    AssignmentController assignmentController;


    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/api/quiz/{quizId}/feedback")
    ResponseEntity<String> getFeedback(@PathVariable int quizId, @RequestBody int studentId) {
        try {
            Map<String,Object> feedback = studentService.getQuizFeedback(quizId,studentId);
            return ResponseEntity.ok(feedback.toString());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/api/submit/quiz/{quizId}")
    ResponseEntity<String> submitQuiz(@PathVariable int quizId, @RequestParam int studentId,@RequestBody Map<String,String> answer) {
        try {
            QuizEntity quiz = quizRepository.findById(quizId);
            studentService.submitQuiz(quizId,studentId, answer);
            ResponseEntity<String> outputFeedback = getFeedback(quizId,studentId);
            String output = "Successfully submitted quiz: " + quizId +"\n" + outputFeedback.getBody();
            return ResponseEntity.ok(output);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/api/view/courses")
    ResponseEntity<String> viewCourses(){
        try {
            String output="";
           List<CourseEntity> courses= courseRepository.findAll();
            for (int i = 0; i < courses.size(); i++) {
                CourseEntity course = courses.get(i);
                output += "Course Id: "+course.getId() +"\n" +
                        "Course Title: " +course.getTitle()+"\n"+
                        "Course Description: "+ course.getDescription()+"\n"+
                        "Course Duration: " +course.getDuration()+"\n"+
                        "---------------------------------------------------------------------------\n";
            }

            return ResponseEntity.ok("Available Courses: \n"+output);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/api/enroll/{studentId}")
    ResponseEntity<String> courseEnroll(@PathVariable int studentId,@RequestParam String courseId){
        try {
            UserEntity student =  userRepository.findUserbyId(studentId);
            if(student != null) {
                courseService.courseEnroll(student, courseId);
                return ResponseEntity.ok("Successfuly Enrolled In Course");
            }
            else return ResponseEntity.ok("No student Found");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/api/course/material")
    ResponseEntity<String> viewCourseMaterial(@RequestParam String courseId){
        try {
             String details = courseService.viewCourseMaterial(courseId);
             return ResponseEntity.ok(details);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/api/attend/lesson/{studentId}")
    ResponseEntity<String> attendLesson(@PathVariable int studentId, @RequestParam int lessonId,@RequestBody String OTP){
        try {
            LessonEntity lesson= lessonService.displayLesson(lessonId);

            String output = studentService.attendLesson(lessonId,studentId,OTP);
            return ResponseEntity.ok(output);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/api/course/media")
    ResponseEntity<String> viewCourseMedia(@RequestParam String courseId){
        try {
            String output = courseService.viewCourseMedia(courseId);

            return ResponseEntity.ok(output);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/api/submit/assigment/{studentId}")
    ResponseEntity<String> submitAssigment(@PathVariable int studentId, @RequestParam int assigmentId,@RequestBody String filePath ){
        try {
            assignmentController.submitAssigment(studentId,assigmentId,filePath);
            return ResponseEntity.ok("Successfully submitted assigment");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/api/assigment/{assigmentId}/feedback")
    ResponseEntity<String> getAssigmenttFeedback(@PathVariable int assigmentId, @RequestParam int studentId) {
        try {
            String feedback= assignmentController.getAssigmentFeedback(assigmentId,studentId);
            return ResponseEntity.ok(feedback);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }







}
