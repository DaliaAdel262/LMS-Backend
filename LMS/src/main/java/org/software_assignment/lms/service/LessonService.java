package org.software_assignment.lms.service;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.LessonEntity;
import org.software_assignment.lms.entity.UserEntity;
import org.software_assignment.lms.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

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

    public String getOTP(int studentID, int lessonID, String courseID) {
        LessonEntity lesson = lessonRepository.findbyId(lessonID);
        if (lesson != null) {
            if (!lesson.getCourseId().equals(courseID)) {
                throw new IllegalArgumentException("Lesson does not belong to the specified course");
            }
            if (!courseService.isStudentEnrolled(courseID, studentID)) {
                throw new SecurityException("Student is not authorized to access this course");
            }
            markAttendance(studentID, lessonID);
            return lesson.getOTP();
        } else {
            throw new NoSuchElementException("Lesson not found");
        }
    }

    public void markAttendance(int studentID, int lessonID) {
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

    public String attendLesson(int lessonId, int studtId, String OTP) throws Exception {
        LessonEntity lesson = displayLesson(lessonId);
        String actualOTP = lesson.getOTP();
        if (Objects.equals(actualOTP, OTP)) {
            markAttendance(studtId, lessonId);
            return "Successfully Attended the lesson";
        }
        throw new Exception("Error: Wrong OTP.. \n Failed to attend the lesson");
    }


    public String getAttendence(int lessonId) {
        LessonEntity lesson = lessonRepository.findbyId(lessonId);
        List<Integer> studentsAttended = lesson.getStudentsAttended();

        CourseEntity course = courseService.getCourseDetails(lesson.getCourseId());
        List<UserEntity> studentsEnrolled = course.getEnrolledStudents();

        System.out.println(lesson.getCourseId());

        String output = "";
        for (int i = 0; i < studentsEnrolled.size(); i++) {
            output += "Student Id: " + studentsEnrolled.get(i).getId();
            if (studentsAttended.contains(studentsEnrolled.get(i).getId())) {
                output += " Attended = True\n";

            } else output += " Attended = False\n";
        }

        return output;
    }

//    // add lesson (intialize lesson from body)
//    public void addLesson(int lessonId, String courseId, String title, String content, int duration) {
//        System.out.println("Adding lesson: " + lessonId + " to course: " + courseId);
//        LessonEntity lesson = new LessonEntity(lessonId, title, content, courseId);
//        lessonRepository.save(lesson);
//        courseService.addLessonToCourse(courseId, lesson, duration);
//        System.out.println("Lesson added successfully.");
//    }

    public LessonEntity addLesson(LessonEntity lessonEntity) {
        if (lessonEntity.getTitle() == null || lessonEntity.getContent() == null || lessonEntity.getCourseId() == null) {
            throw new IllegalArgumentException("Lesson details are incomplete.");
        }
        return lessonRepository.save(lessonEntity);
    }

}