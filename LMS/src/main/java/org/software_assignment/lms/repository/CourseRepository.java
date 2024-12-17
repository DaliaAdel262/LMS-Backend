package org.software_assignment.lms.repository;

import java.util.*;
import org.software_assignment.lms.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

    private List<CourseEntity> courses = new ArrayList<>(Arrays.asList(
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