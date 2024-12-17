package org.software_assignment.lms.entity;

import java.util.HashMap;
import java.util.Map;

public class AssignmentEntity {
    private int assignmentID;
    private String assignmentName;
    private String assignmentDescription;
    private String dueDate;
    private String courseId;
    Map<Integer, String> studentSubmissions;
    public AssignmentEntity() {}

    public AssignmentEntity(int assignmentID, String assignmentName, String assignmentDescription, String dueDate, String courseId) {
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.assignmentDescription = assignmentDescription;
        this.dueDate = dueDate;
        this.courseId = courseId;
        this.studentSubmissions = new HashMap<>();
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

    public Map<Integer,String> getStudentSubmission(){
        return this.studentSubmissions;
    }
}
