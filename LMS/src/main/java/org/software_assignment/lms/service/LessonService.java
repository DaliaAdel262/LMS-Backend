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
        List<Integer> studentsAttended = lesson.getStudentsAttended();
        if (!studentsAttended.contains(studentID)) {
            lesson.addStudent(studentID);
            lessonRepository.save(lesson);
        }
    }
    public String generateOTP(int lessonID) {
        LessonEntity lesson = lessonRepository.findbyId(lessonID);
        if (lesson == null) {
            throw new NoSuchElementException("Lesson not found");
        }
        String otp = String.format("%04d", new Random().nextInt(10000)); // Generates a 4-digit OTP
        lesson.setOTP(otp);
        lessonRepository.save(lesson);
        return otp;
    }
    public LessonEntity displayLesson(int lessonID) {
        LessonEntity lesson = lessonRepository.findbyId(lessonID);
        if (lesson == null) {
            throw new NoSuchElementException("Lesson not found");
        }
        return lesson;
    }

}
