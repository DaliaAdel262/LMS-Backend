package org.software_assignment.lms.repository;

import org.software_assignment.lms.entity.CourseEntity;
import java.util.*;
import org.software_assignment.lms.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentRepo {
    private List<AssignmentEntity> assignments = new ArrayList<>();

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
