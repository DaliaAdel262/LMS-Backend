package org.software_assignment.lms.entity;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Student extends UserEntity{
    public Student(int id, String name, String birthday, String email, String password) {
        super(id, name, birthday, email, password);
    }
    private Map<CourseEntity,Double> coursesGrade = new HashMap();
    public void addCourse (CourseEntity course){
        coursesGrade.put(course,null);
    }
    public void deleteCourse (CourseEntity course){
        coursesGrade.remove(course);
    }
}

