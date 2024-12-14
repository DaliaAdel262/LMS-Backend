package org.software_assignment.lms.service;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.QuizEntity;
import org.software_assignment.lms.entity.LessonEntity;
import org.software_assignment.lms.entity.AssignmentEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;

@Service
public class CourseService {
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
                   new ArrayList<>(List.of("1", "2")),  // Ensure this list is mutable
                   Map.of(
                           "What is Java?", "A programming language",
                           "What is JVM?", "Java Virtual Machine"
                   )
           )

   ));
   //get all courses
   public List<CourseEntity>findAll(){
        return data;
    }
public boolean deleteStudent( String courseId,int studentId){
 for(CourseEntity course:data){
    if(course.getId().equals(courseId)){
       //search for student that is found in array of enrolled students in this course
      String studentIdStr=String.valueOf(studentId);
      List<String> enrolledStudents = course.getEnrolledStudents();
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
}
