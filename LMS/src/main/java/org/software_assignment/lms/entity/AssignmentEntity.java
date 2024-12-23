package org.software_assignment.lms.entity;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class AssignmentEntity {
    private int assignmentID;
    private String assignmentName;
    private String assignmentDescription;
    private String filePath;
    private String dueDate;
    private String courseId;
    Map<Integer, String> studentSubmissions=new HashMap<>();
    Map<Integer,Double> studentsGrades = new HashMap<>();
    public AssignmentEntity() {}
    public AssignmentEntity(int assignmentID, String assignmentName, String assignmentDescription, String dueDate, String filePath, String courseId) {
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.assignmentDescription = assignmentDescription;
        this.dueDate = dueDate;
        this.courseId = courseId;
        this.filePath = filePath;
        this.studentSubmissions = new HashMap<>();
    }


    public AssignmentEntity(int i, String s, String writeAJavaProgram, String date, String number) {
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Map<Integer,String> getStudentSubmission(){
        return this.studentSubmissions;
    }

    public void addSubmission(int student_id, String filePath){
        this.studentSubmissions.put(student_id, filePath);
    }

    public void addStudentsGrades(int studentId, double grade){
        studentsGrades.put(studentId,grade);
    }



    public Double getSingleStudentGrade(int studentId) {
      return studentsGrades.get(studentId);
    }
}
