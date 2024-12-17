package org.software_assignment.lms.repository;

import org.software_assignment.lms.entity.CourseEntity;
import java.util.*;
import org.software_assignment.lms.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentRepo {
    private List<AssignmentEntity> assignments = new ArrayList<>;
    public List<CourseEntity> findAll() {
        return new ArrayList<>(courses);
    }

    public CourseEntity findById(String id) {
        for (CourseEntity course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    public boolean deleteById(String id) {
        return courses.removeIf(course -> course.getId().equals(id));
    }

    public CourseEntity save(CourseEntity course) {
        if(findById(course.getId()).equals(null)){
            courses.add(course);
        }else{
            deleteById(course.getId());
            courses.add(course);
        }
        return course;
    }
}
