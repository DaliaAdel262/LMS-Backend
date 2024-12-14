package org.software_assignment.lms.entity;

public class AssignmentEntity {
    private int assignmentID;
    private String assignmentName;
    private String assignmentDescription;
    private String dueDate;
    private String courseId;
    public AssignmentEntity() {}
    public AssignmentEntity(int assignmentID, String assignmentName, String assignmentDescription, String dueDate, String courseId) {
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.assignmentDescription = assignmentDescription;
        this.dueDate = dueDate;
        this.courseId = courseId;

    }
}
