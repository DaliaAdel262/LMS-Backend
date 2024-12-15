package org.software_assignment.lms.service;

import org.software_assignment.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CourseService {
    
    @Autowired
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

   
   public void deleteCourseById(String id) {
       if (courseRepository.findById(id) == null) {
        throw new NoSuchElementException("Course with ID " + id + " does not exist.");
       }
       courseRepository.deleteById(id);
   }


   public CourseEntity getCourseDetails(String id){
     CourseEntity course = courseRepository.findById(id);
     if (course == null) {
        throw new NoSuchElementException("Course with ID " + id + " does not exist.");
     }
     return course;
   }
   //display enrolled students in course
    public List<Student> getStudentsByCourseId(String courseId) {
        List<CourseEntity> data = courseRepository.findAll();    
        for (CourseEntity course : data) {
            if (course.getId().equals(courseId)) {
                return course.getEnrolledStudents();
            }
        }
        return new ArrayList<>();
    }
    
    public String addQuestionToCourse(String courseId, String question, String answer) {
        List<CourseEntity> data = courseRepository.findAll();    
        for (CourseEntity course : data) {
            if (course.getId().equals(courseId)) {
                if (course.getQuestionBank() == null) {
                    throw new IllegalStateException("Question bank is not initialized for this course");
                }
                course.getQuestionBank().put(question, answer); // Add question-answer pair
                return "Question added successfully";
            }
        }
        throw new IllegalArgumentException("Course with ID " + courseId + " not found");
    }


}
