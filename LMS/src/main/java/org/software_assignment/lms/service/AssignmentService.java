package org.software_assignment.lms.service;
import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.*;

import org.software_assignment.lms.entity.AssignmentEntity;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.UserEntity;
import org.software_assignment.lms.repository.AssignmentRepo;
import org.software_assignment.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

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

            if ( studentSubmission==null || !studentSubmission.containsKey(studentId)) {
                assignment.addSubmission(studentId, submissionPath);
            } else {
                studentSubmission.put(studentId, submissionPath);
            }
//
//            if (studentSubmission.containsKey(studentId)) {
//                studentSubmission.put(studentId, submissionPath);
//            } else {
//                assignment.addSubmission(studentId, submissionPath);
//            }
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

    public void addAssigment(AssignmentEntity assignment){
        CourseEntity course = courseRepository.findById(assignment.getCourseId());
        course.addAssigment(assignment);
        // add assigment to repo
        assignmentRepo.save(assignment);

    }

    public AssignmentEntity getAssigment(int id){
       return assignmentRepo.findById(id);
    }

    public void addStudentGrade(int assigmentId, int studentId, Double grade){
        AssignmentEntity assigment = getAssigment(assigmentId);
        assigment.addStudentsGrades(studentId,grade);
    }
    public String getFeedback(int assigmentId,int studentId){
        AssignmentEntity assigment = getAssigment(assigmentId);
        Double grade = assigment.getSingleStudentGrade(studentId);
        if(grade==null) return "Assigment is not graded";
        return "Your feedback: "+grade;
    }

    public String getAssigemntSubmissions(int assigmentId){
        AssignmentEntity assignment = getAssigment(assigmentId);

        String courseId = assignment.getCourseId();
        CourseEntity course = courseService.getCourseDetails(courseId);
        List<UserEntity> students= course.getEnrolledStudents();

        Map<Integer, String> studentSubmissions = assignment.getStudentSubmission();
        String output="";
        for(int i=0;i<students.size();i++){
            output+= "Student Id: "+students.get(i).getId();
            if(studentSubmissions.containsKey(students.get(i).getId())){
                output+=" submission = True \n";
            }
            else output+=" submission = False \n";
        }
        return output;
    }


}
