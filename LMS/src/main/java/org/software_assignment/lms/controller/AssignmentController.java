package org.software_assignment.lms.controller;

import java.util.NoSuchElementException;
import java.nio.file.*;
import org.apache.catalina.connector.Response;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.catalina.User;
import org.software_assignment.lms.entity.AssignmentEntity;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.UserEntity;
import org.software_assignment.lms.service.AssignmentService;
import org.software_assignment.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(value = "api/assignments")
public class AssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/{assignmentID}/{student_id}/submissions")
    private ResponseEntity<String> addSubmission(@PathVariable int studentID,@PathVariable int assignmentID,@RequestParam String filePath){
        try {
            assignmentService.addStudentSubmission(studentID, assignmentID, filePath);
            return ResponseEntity.status(HttpStatus.CREATED).body("Submission added successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
        
    }

    @GetMapping(value = "/{assignmentID}/{student_id}/grade")
     ResponseEntity<String> gradeAssignment(@PathVariable int assignmentID, @PathVariable int studentID){
        try{

            String studentSubmission = assignmentService.gradeAssignment(studentID, assignmentID);
            if("Student hasn't submitted assignment".equals(studentSubmission)){
                return ResponseEntity.status(HttpStatus.OK).body("Student hasn't submitted the assignment. Grade them as 0.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(studentSubmission);
            
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
        
    }

    @GetMapping(value = "/{assignmentID}")
     ResponseEntity<?> getAssignment(@PathVariable int assignmentID){
        try {
            String filePath = assignmentService.getAssignmentFilePath(assignmentID);
            
            Path path = Paths.get(filePath);
            Resource resource = new FileSystemResource(path);
            
            if (!resource.exists()) {
                throw new NoSuchElementException("File not found");
            }
    
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM) 
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
                    
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment file not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


    public void submitAssigment(int studentId, int assigmentId, String filePath) {
        assignmentService.addStudentSubmission(studentId,assigmentId,filePath);
    }
    public String getAssigmentFeedback(int assigmentId,int studentId){
        return assignmentService.getFeedback(assigmentId,studentId);
    }

    public String getAssigemntSubmissions(int assigmentId){
       return assignmentService.getAssigemntSubmissions(assigmentId);
    }


}
