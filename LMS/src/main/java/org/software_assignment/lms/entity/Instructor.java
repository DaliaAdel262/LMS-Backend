package org.software_assignment.lms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Instructor extends UserEntity{

    public void createCourse(String id, String title, String description, int duration, int instructorId,
                              List<LessonEntity> lessons, List<AssignmentEntity> assignments, List<QuizEntity> quizzes,
                              List<String> enrolledStudents, Map<String, String> questionBank){

        CourseEntity course = new CourseEntity();

    }

}
