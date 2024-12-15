package org.software_assignment.lms.service;
import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    private List<CourseEntity> data = new ArrayList<>(Arrays.asList(
            new CourseEntity(
                    "1",
                    "Java Basics",
                    "Introduction to Java programming",
                    30,
                    101,
                    new ArrayList<>(List.of(
                            new LessonEntity(1, "Lesson 1", "Introduction to Java", "1"),
                            new LessonEntity(2, "Lesson 2", "Data types in Java", "1"),
                            new LessonEntity(3, "Lesson 3", "Control structures in Java", "1")
                    )),
                    new ArrayList<>(List.of(
                            new AssignmentEntity(1, "Assignment 1", "Write a Java program", "2024-12-01", "1"),
                            new AssignmentEntity(2, "Assignment 2", "Implement a Java class", "2024-12-10", "1")
                    )),
                    new ArrayList<>(List.of(
                            new QuizEntity(1, "Quiz 1", "1", "Java Basics Quiz", 10)
                    )),
                    new ArrayList<>(List.of(
                            new Student(1, "Mariam", "2005", "mariameid33@gmail.com", "ra5"),
                            new Student(2, "Bob", "1999-02-02", "bob@example.com", "password456")
                    )),
                    new HashMap<>(Map.of(
                            "What is Java?", "A programming language",
                            "What is JVM?", "Java Virtual Machine"
                    ))
            )
    ));

    //get all courses
   public List<CourseEntity>findAll(){
     return  courseRepository.findAll();
    }
public boolean deleteStudent( String courseId,int studentId){
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
   //display enrolled students in course
    public List<Student> getStudentsByCourseId(String courseId) {
        for (CourseEntity course : data) {
            if (course.getId().equals(courseId)) {
                return course.getEnrolledStudents();
            }
        }
        return new ArrayList<>();
    }
    public String addQuestionToCourse(String courseId, String question, String answer) {
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
