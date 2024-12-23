package org.software_assignment.lms.controller;

import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.CourseRepository;
import org.software_assignment.lms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController()
public class InstructorController {
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private AssignmentController assignmentController;
    @Autowired
    private AssignmentService assignmentService;

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

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/api/track/quiz/{quizId}")
    ResponseEntity<String> trackQuizzes(@PathVariable int quizId){
        try{
            String output = quizService.trackQuiz(quizId);
            double avegrage = quizService.averageScore(quizId);
            output ="Average grade: "+ avegrage+"\n"+ output;
            return ResponseEntity.ok(output);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/course/{courseId}/add/mediaFile")
    ResponseEntity<String> addMediaFile(@PathVariable String courseId, @RequestBody MediaFile mediaFile){
        try{
            courseService.addMediaFile(courseId,mediaFile);

            return ResponseEntity.ok("Successfully added Media File");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @GetMapping("/api/view/students/course/")
    ResponseEntity<String> viewStudents(@RequestParam String courseId){
        try{
            List<UserEntity> students= courseService.getStudentsByCourseId(courseId);
            System.out.println(students.size());
            String output = "";
            for(int i=0;i<students.size();i++){
                UserEntity student =students.get(i);

                output+="Student ID: " +student.getId()+"\n"+
                        "Student Name: "+ student.getName()+'\n'+
                        "Student Email: " +student.getEmail()+'\n'+
                        "---------------------------------------------------------------------------\n";

            }
            if(output.isEmpty()) output = "No Students Enrolled";
            return ResponseEntity.ok(output);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/lesson/{lessonId}/generate/OTP")
    ResponseEntity<String> generateOTP(@PathVariable int lessonId){
        try{

            String OTP="OTP already Exists";
            if(lessonService.displayLesson(lessonId).getOTP()==null )
                 OTP = "Successfully generated OTP: " +lessonService.generateOTP(lessonId);

            return ResponseEntity.ok(OTP);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/course/add/questionBank")
    ResponseEntity<String> addQuestionBank(@RequestParam String courseId, @RequestBody Map<String,String> questionBank ){
        try{
            for(Map.Entry<String, String> question: questionBank.entrySet()){
                courseService.addQuestionToCourse(courseId,question.getKey(),question.getValue());
            }
            return ResponseEntity.ok("successfully added questions to question bank");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/assigment/grade/{assignmentID}/{student_id}")
    ResponseEntity<String> gradeAssignment(@PathVariable int assignmentID, @PathVariable int studentID){
           return assignmentController.gradeAssignment(assignmentID,studentID);
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','STUDENT')")
    @GetMapping("/api/assigment/{assignmentID}")
    ResponseEntity<?> getAssignment(@PathVariable int assignmentID){
        return assignmentController.getAssignment(assignmentID);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/add/assigment")
    ResponseEntity<?> addAssigment(@RequestBody AssignmentEntity assignment){
        try{
            System.out.println( assignment.getAssignmentID() + " "+ assignment.getAssignmentName());
            assignmentService.addAssigment(assignment);
            return ResponseEntity.ok("successfully added Assigment: " + assignment.getAssignmentID() +" to Course "+assignment.getCourseId());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/add/lesson")
    ResponseEntity<?> addLesson(@RequestBody LessonEntity lesson){
        try{

            lessonService.addLesson(lesson);
            return ResponseEntity.ok("successfully added Assigment: " +lesson.getId() +" to Course "+lesson.getCourseId());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/api/view/assigment/{assignmentId}/{studentId}")
    ResponseEntity<?> viewAssigment(@PathVariable int assignmentId, @PathVariable int studentId){
        try{
            return assignmentController.gradeAssignment(assignmentId,studentId);
           // return ResponseEntity.ok("successfully added Assigment: " + assignment.getAssignmentID() +" to Course "+assignment.getCourseId());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/api/grade/assigment/{assignmentId}/{studentId}")
    ResponseEntity<?> gradeStudent(@PathVariable int assignmentId, @PathVariable int studentId,@RequestBody Double grade){
        try{
            assignmentService.addStudentGrade(assignmentId,studentId,grade);
             return ResponseEntity.ok("successfully Graded student: " +studentId+"  for assigment: "+ assignmentId);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/api/track/assigment/{assignmentId}")
    ResponseEntity<String> trackAssigment(@PathVariable int assignmentId){
        try{
            String output = assignmentController.getAssigemntSubmissions(assignmentId);
            return ResponseEntity.ok(output);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/api/track/lesson/{lessonId}")
    ResponseEntity<String> trackLesson(@PathVariable int lessonId){
        try{
            String output = lessonService.getAttendence(lessonId);
            return ResponseEntity.ok(output);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
