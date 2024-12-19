package org.software_assignment.lms.service;
import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //get all courses
    public List<CourseEntity>findAll(){
        return courseRepository.findAll();
    }
    public boolean deleteStudent(String courseId, int studentId) {

        List<CourseEntity> courses = courseRepository.findAll();

        // Find the course by ID using a stream
        CourseEntity course = courses.stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst()
                .orElse(null);

        if (course == null) {
            System.out.println("Course with ID " + courseId + " not found.");
            return false;
        }

        List<Student> enrolledStudents = course.getEnrolledStudents();

        if (enrolledStudents != null) {

            Student studentToRemove = enrolledStudents.stream()
                    .filter(student -> student.getId() == studentId)
                    .findFirst()
                    .orElse(null);

            if (studentToRemove != null) {
                enrolledStudents.remove(studentToRemove);
                course.setEnrolledStudents(enrolledStudents);
                courseRepository.save(course);
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
                course.getQuestionBank().put(question, answer);
                courseRepository.save(course);
                return "Question added successfully";
            }
        }
        throw new IllegalArgumentException("Course with ID " + courseId + " not found");
    }

   public CourseEntity addCourse(String id, String title, String desc, int instructorID) {
       CourseEntity course = new CourseEntity(); 
       course.setId(id);
       course.setTitle(title);
       course.setDescription(desc);
       course.setInstructorId(instructorID);
       course.setDuration(0);
       return courseRepository.save(course);
   }


}