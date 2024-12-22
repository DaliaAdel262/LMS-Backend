package org.software_assignment.lms.service;

import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.LessonEntity;
import org.software_assignment.lms.entity.UserEntity;
import org.software_assignment.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class StudentService {
    @Autowired
    QuizService quizService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    LessonService lessonService;
    @Autowired
    UserService userService;

    public Map<String,Object> getQuizFeedback(int quizId, int studentId){
        //QuizEntity quiz =  quizService.getQuizById(quizId);
        return quizService.gradeQuiz(quizId, studentId);
    }
    public void enrollCourse(String courseId){
        CourseEntity course = courseRepository.findById(courseId);

    }
    public void submitQuiz(int quizId,int studtId, Map<String,String> answer) throws Exception {
         quizService.submitQuiz(quizId,studtId,answer);
    }

    public String attendLesson(int lessonId,int studtId,String OTP) throws Exception {
        String courseId = lessonService.displayLesson(lessonId).getCourseId();
        UserEntity student = userService.findUserbyId(studtId);
        if(courseRepository.findById(courseId).getEnrolledStudents().contains(student))
            return lessonService.attendLesson(lessonId,studtId,OTP);
        return "You don't belong to this course";
    }




}
