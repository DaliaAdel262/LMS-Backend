package org.software_assignment.lms.service;

import org.software_assignment.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService {
    
    private CourseRepository courseRepository;

    //get all courses
   public List<CourseEntity>findAll(){
        return courseRepository.findAll();
    }

   public boolean deleteStudent( String courseId,int studentId){
        List<CourseEntity> data = courseRepository.findAll();
   for(CourseEntity course:data){
    if(course.getId().equals(courseId)){
       //search for student that is found in array of enrolled students in this course
      String studentIdStr=String.valueOf(studentId);
      List<Student> enrolledStudents = course.getEnrolledStudents();
       if (enrolledStudents != null && enrolledStudents.contains(studentIdStr)) {
          enrolledStudents.remove(studentIdStr);
          course.setEnrolledStudents(enrolledStudents);//modified enrolled students after removal
          System.out.println("Student with ID " + studentId + " removed from course " + courseId);
          return true;
       } else {
          System.out.println("Student with ID " + studentId + " not found in course " + courseId);
          return false;
       }
    }

   } System.out.println("Course with ID " + courseId + " not found.");
     return false;

   }

   @Autowired

//    public void deleteCourseById(String id) {
//        if (!courseRepository.findById(id)) {
//            throw new ResourceNotFoundException("Course with ID " + id + " does not exist.");
//        }
//        courseRepository.deleteById(id);
   }
}
