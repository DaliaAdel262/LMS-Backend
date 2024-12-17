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
                            new QuizEntity(
                                    1,
                                    "Quiz 1",
                                    "1",
                                    3,
                                    new HashMap<>(Map.of(
                                            "What is Java?", "A programming language",
                                            "What is JVM?", "Java Virtual Machine",
                                            "Explain OOP concepts in Java", "Inheritance, Polymorphism, Encapsulation, Abstraction"
                                    ))
                            ),
                            new QuizEntity(
                                    2,
                                    "Quiz 2",
                                    "1",
                                    3,
                                    new HashMap<>(Map.of(
                                            "What are Java data types?", "Primitive and Non-Primitive",
                                            "Explain the use of 'final' keyword", "To define constants or prevent inheritance",
                                            "What is the purpose of garbage collection?", "To free up unused memory"
                                    ))
                            )
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
     return  data;
    }

    public boolean deleteStudent(String courseId, int studentId) {

        for (CourseEntity course : data) {

            if (course.getId().equals(courseId)) {
                List<Student> enrolledStudents = course.getEnrolledStudents();

                if (enrolledStudents != null) {
                    Student studentToRemove = null;
                    for (Student student : enrolledStudents) {
                        if (student.getId() == studentId) {
                            studentToRemove = student;
                            break;
                        }
                    }

                    // If the student is found, remove it from the list
                    if (studentToRemove != null) {
                        enrolledStudents.remove(studentToRemove);
                        course.setEnrolledStudents(enrolledStudents);  // Update the list of enrolled students
                        System.out.println("Student with ID " + studentId + " removed from course " + courseId);
                        return true;
                    } else {
                        System.out.println("Student with ID " + studentId + " not found in course " + courseId);
                        return false;
                    }
                } else {
                    System.out.println("No students enrolled in course " + courseId);
                    return false;
                }
            }
        }

        System.out.println("Course with ID " + courseId + " not found.");
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
