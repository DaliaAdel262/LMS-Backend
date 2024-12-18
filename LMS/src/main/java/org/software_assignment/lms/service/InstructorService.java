package org.software_assignment.lms.service;

import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.QuizEntity;
import org.software_assignment.lms.entity.Student;
import org.software_assignment.lms.repository.CourseRepository;
import org.software_assignment.lms.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    QuizRepository quizRepository;

    public  void addCourse(CourseEntity course)
    {
        courseRepository.save(course);
    }
    public  void addQuiz(QuizEntity quiz)
    {
        quizRepository.save(quiz);
    }
    public  void  removeStudent(String courseId , int stdID){
        CourseEntity courseEntity = courseRepository.findById(courseId);
        if(courseEntity == null)
            throw new RuntimeException("Course not found");
        List< Student> std = courseEntity.getEnrolledStudents();
        for (Student student : std) {
            if (student.getId() == stdID) {
                std.remove(student);
                courseEntity.setEnrolledStudents(std);
                courseRepository.save(courseEntity);
                return;
            }
        }
        throw new RuntimeException("Student not found");
    }
}
