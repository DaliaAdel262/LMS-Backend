package org.software_assignment.lms.service;

import java.util.*;

import org.software_assignment.lms.entity.LessonEntity;
import org.software_assignment.lms.entity.Student;
import org.software_assignment.lms.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;

    public LessonService(LessonRepository lessonRepository, CourseService courseService) {
        this.lessonRepository = lessonRepository;
        this.courseService = courseService;
    }

    public String getOTP(int studentID, int lessonID, String courseID){
        LessonEntity lesson = lessonRepository.findbyId(lessonID);
        if(lesson!=null){
            if (!lesson.getCourseId().equals(courseID)) {
                throw new IllegalArgumentException("Lesson does not belong to the specified course");
            }
            if (!courseService.isStudentEnrolled(courseID, studentID)) {
                throw new SecurityException("Student is not authorized to access this course");
            }
            markAttendance(studentID, lessonID);
            return lesson.getOTP();
        }else{
            throw new NoSuchElementException("Lesson not found");
        }
    }

    public void markAttendance(int studentID, int lessonID){
        LessonEntity lesson = lessonRepository.findbyId(lessonID);
        if (lesson == null) {
            throw new NoSuchElementException("Lesson not found");
        }
        List<Student> studentsAttended = lesson.getStudentsAttended();
        for(Student student:studentsAttended){
            if(student.getId() == studentID){
                return;
            }
        }
        // need logic for getting instance of student to add to array, then add student and save lesson with lessonRepository
    }
}
