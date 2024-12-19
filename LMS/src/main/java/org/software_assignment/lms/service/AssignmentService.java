package org.software_assignment.lms.service;
import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    public AssignmentService(AssignmentRepo assignmentRepo){
        this.assignmentRepo = assignmentRepo;
    }

    public void addStudentSubmission(int studentId, int assignmentID, String submissionPath){
        AssignmentEntity assignment = assignmentRepo.findById(assignmentID);
        if(assignment==null){

            throw new NoSuchElementException("Assignment not found");

        }else{

            String dueDateString = assignment.getDueDate(); 
            LocalDate dueDate = LocalDate.parse(dueDateString, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate currentDate = LocalDate.now();
            
            if (currentDate.isAfter(dueDate)) {
                throw new IllegalArgumentException("Cannot submit the assignment. The due date has passed.");
            }
            Map<Integer,String> studentSubmission = assignment.getStudentSubmission();
            if (studentSubmission.containsKey(studentId)) {
                studentSubmission.put(studentId, submissionPath);
            } else {
                assignment.addSubmission(studentId, submissionPath);
            }
            assignmentRepo.save(assignment);
            
        }
    }
    
    public String gradeAssignment(int studentId, int assignmentID){
        AssignmentEntity assignment = assignmentRepo.findById(assignmentID);
        if(assignment==null){
            throw new NoSuchElementException("Assignment not found");
        }else{
            Map<Integer,String> studenSubmission = assignment.getStudentSubmission();
            if(studenSubmission.containsKey(studentId)){
                return studenSubmission.get(studentId);
            }else{
                return "Student hasn't submitted assignment";
            }
        }
    }

    public String getAssignmentFilePath(int assignmentID) {
        AssignmentEntity assignment = assignmentRepo.findById(assignmentID);
        if (assignment == null) {
            throw new NoSuchElementException("Assignment not found");
        }
        return assignment.getFilePath();
    }
}
