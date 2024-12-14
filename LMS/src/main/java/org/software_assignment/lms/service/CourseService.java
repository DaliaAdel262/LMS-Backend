package org.software_assignment.lms.service;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.QuizEntity;
import org.software_assignment.lms.entity.LessonEntity;
import org.software_assignment.lms.entity.AssignmentEntity;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
public class CourseService {
   private List<CourseEntity> data = new ArrayList<>(Arrays.asList(
           new CourseEntity(
                   "1",
                   "Java Basics",
                   "Introduction to Java programming",
                   30,
                   101,
                   List.of(
                           new LessonEntity(1, "Lesson 1", "Introduction to Java", "1"),
                           new LessonEntity(2, "Lesson 2", "Data types in Java", "1"),
                           new LessonEntity(3, "Lesson 3", "Control structures in Java", "1")
                   ),
                   List.of(
                           new AssignmentEntity(1, "Assignment 1", "Write a Java program", "2024-12-01", "1"),
                           new AssignmentEntity(2, "Assignment 2", "Implement a Java class", "2024-12-10", "1")
                   ),
                   List.of(
                           new QuizEntity(1, "Quiz 1", "1", "Java Basics Quiz", 10)
                   ),
                   List.of("Student 1", "Student 2"),
                   Map.of(
                           "What is Java?", "A programming language",
                           "What is JVM?", "Java Virtual Machine"
                   )
           ),
           new CourseEntity(
                   "2",
                   "Web Development",
                   "Learn HTML, CSS, and JavaScript",
                   40,
                   102,
                   List.of(
                           new LessonEntity(1, "Lesson 1: HTML Basics", "Introduction to HTML", "2"),
                           new LessonEntity(2, "Lesson 2: CSS Basics", "Introduction to CSS", "2"),
                           new LessonEntity(3, "Lesson 3: JS Basics", "Introduction to JavaScript", "2")
                   ),
                   List.of(
                           new AssignmentEntity(1, "Assignment 1", "Build a simple webpage", "2024-12-05", "2"),
                           new AssignmentEntity(2, "Assignment 2", "Add CSS to the webpage", "2024-12-12", "2")
                   ),
                   List.of(
                           new QuizEntity(1, "Quiz 1", "2", "HTML Basics Quiz", 10)
                   ),
                   List.of("Student 3", "Student 4"),
                   Map.of(
                           "What is HTML?", "A markup language for web pages",
                           "What is CSS?", "Cascading Style Sheets"
                   )
           ),
           new CourseEntity(
                   "3",
                   "Data Structures",
                   "Learn about Arrays, Linked Lists, and Trees",
                   50,
                   103,
                   List.of(
                           new LessonEntity(1, "Lesson 1: Arrays", "Introduction to Arrays", "3"),
                           new LessonEntity(2, "Lesson 2: Linked Lists", "Introduction to Linked Lists", "3"),
                           new LessonEntity(3, "Lesson 3: Trees", "Introduction to Trees", "3")
                   ),
                   List.of(
                           new AssignmentEntity(1, "Assignment 1", "Implement an array", "2024-12-08", "3"),
                           new AssignmentEntity(2, "Assignment 2", "Linked list operations", "2024-12-15", "3")
                   ),
                   List.of(
                           new QuizEntity(1, "Quiz 1", "3", "Array Basics Quiz", 10),
                           new QuizEntity(2, "Quiz 2", "3", "Linked List Basics Quiz", 10)
                   ),
                   List.of("Student 5", "Student 6"),
                   Map.of(
                           "What is an Array?", "A collection of elements of the same type",
                           "What is a Linked List?", "A data structure with nodes linked together"
                   )
           )
   ));

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
