package org.software_assignment.lms.repository;

import org.software_assignment.lms.entity.AssignmentEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AssignmentRepo {
    private List<AssignmentEntity> assignments = new ArrayList<>(Arrays.asList(
        new AssignmentEntity(
                1, 
                "Assignment 1", 
                "Write a Java program", 
                "2024-12-01", 
                "src/main/resources/assignment-files/Advanced SWE - Project 2024.pdf", 
                "1" 
        ),
            new AssignmentEntity(
                2, 
                "Assignment 2", 
                "Implement a Java class", 
                "2025-01-05", 
                "src/main/resources/assignment-files/Advanced SWE - Project 2024.pdf", 
                "1" 
        )
    ));

    public List<AssignmentEntity> findAll() {
        return new ArrayList<>(assignments);
    }

    public AssignmentEntity findById(int id) {
        for (AssignmentEntity assignment : assignments) {
            if (assignment.getAssignmentID() == id) {
                return assignment;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        return assignments.removeIf(assignment -> assignment.getAssignmentID() == id);
    }

    public AssignmentEntity save(AssignmentEntity assignment) {
        if(findById(assignment.getAssignmentID()) == null){
            assignments.add(assignment);
        }else{
            deleteById(assignment.getAssignmentID());
            assignments.add(assignment);
        }
        return assignment;
    }

}
