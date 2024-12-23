package org.software_assignment.lms.service;

import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.CourseRepository;
import org.software_assignment.lms.repository.LessonRepository;
import org.software_assignment.lms.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class InstructorService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    LessonRepository lessonRepository;

    public  void addCourse(CourseEntity course)
    {
        courseRepository.save(course);
    }

    public  void  removeStudent(String courseId , int stdID){
        CourseEntity courseEntity = courseRepository.findById(courseId);
        if(courseEntity == null)
            throw new RuntimeException("Course not found");
        List<UserEntity> std = courseEntity.getEnrolledStudents();
        for (UserEntity student : std) {
            if (student.getId() == stdID) {
                std.remove(student);
                courseEntity.setEnrolledStudents(std);
                courseRepository.save(courseEntity);
                return;
            }
        }
        throw new RuntimeException("Student not found");
    }

    public Map<Integer,Integer> trackQuiz(int quizId){
        return quizRepository.findById(quizId).getStudentGrades();
    }


    public void addLesson(LessonEntity lesson){
        lessonRepository.save(lesson);
    }

    public void addQuiz(QuizEntity quiz){
        quizRepository.save(quiz);
    }

}
