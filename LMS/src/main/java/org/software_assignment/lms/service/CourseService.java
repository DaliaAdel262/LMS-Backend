package org.software_assignment.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    //get all courses
    public List<CourseEntity> findAll() {
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

        List<UserEntity> enrolledStudents = course.getEnrolledStudents();

        if (enrolledStudents != null) {

            UserEntity studentToRemove = enrolledStudents.stream()
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

public void deleteCourseById(String id) {
        if (courseRepository.findById(id) == null) {
         throw new NoSuchElementException("Course with ID " + id + " does not exist.");
        }
        courseRepository.deleteById(id);
    }
    public CourseEntity getCourseDetails(String id) {
        CourseEntity course = courseRepository.findById(id);
        if (course == null) {
            throw new NoSuchElementException("Course with ID " + id + " does not exist.");
        }
        return course;
    }

    //display enrolled students in course
    public List<UserEntity> getStudentsByCourseId(String courseId) {
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

    public boolean isStudentEnrolled(String courseID, int studentID) {
        CourseEntity course = courseRepository.findById(courseID);
        if (course != null) {
            List<UserEntity> studentsEnrolled = course.getEnrolledStudents();
            for (UserEntity student : studentsEnrolled) {
                if (student.getId() == studentID) {
                    return true;
                }
            }
            return false;
        } else {
            throw new NoSuchElementException("Course not found");
        }
    }

    public void courseEnroll(UserEntity student, String courseId) {
        CourseEntity course = courseRepository.findById(courseId);
        course.enrollStudet(student);
    }

    public void addMediaFile(String courseId, MediaFile mediaFile) {
        CourseEntity course = courseRepository.findById(courseId);
        course.addMediaFiles(mediaFile);
    }

    public String viewCourseMaterial(String courseId) {
        CourseEntity course = getCourseDetails(courseId);
        List<LessonEntity> lessons = course.getLessons();
        List<MediaFile> mediaFiels = course.getMediaFiles();
        List<AssignmentEntity> assignments = course.getAssignments();
        List<QuizEntity> quizzes = course.getQuizzes();

        String lessonDetails = "Lessons\n";
        if (lessons != null)
            for (int i = 0; i < lessons.size(); i++) {
                LessonEntity lesson = lessons.get(i);
                lessonDetails += "Lesson Title: " + lesson.getTitle() + '\n' +
                        "Lessons Content: " + lesson.getContent() + "\n";
            }
        String mediaDetails = "Media Files:\n";
        if (mediaFiels != null)
            for (int i = 0; i < mediaFiels.size(); i++) {
                MediaFile mediaFile = mediaFiels.get(i);
                mediaDetails += mediaFile.getFileType() + '\n';
            }
        String quizDetails = "Quizzes:\n";
        if (quizzes != null)
            for (int i = 0; i < quizzes.size(); i++) {
                QuizEntity quiz = quizzes.get(i);
                quizDetails += "Quiz Title: " + quiz.getTitle() + "\n" +
                        "Number of Questions: " + quiz.getNumberOfQuestions() + '\n';
            }

        String assignmentDetailss = "";
        if (assignments != null)
            for (int i = 0; i < assignments.size(); i++) {
                AssignmentEntity assignment = assignments.get(i);
                assignmentDetailss += "Assignemnt Title: " + assignment.getAssignmentName() + "\n" +
                        "Assignemnt description: " + assignment.getAssignmentDescription() + '\n';
            }
        String output = "Course Material: \n" + lessonDetails +
                "-------------------------------------------------\n" +
                mediaDetails + "-------------------------------------------------\n" +
                assignmentDetailss + "-------------------------------------------------\n" +
                quizDetails + "-------------------------------------------------\n";

        return output;


    }

    public String viewCourseMedia(String courseId) {
        CourseEntity course = courseRepository.findById(courseId);
        List<MediaFile> medias = course.getMediaFiles();
        List<Path> filePaths = new ArrayList<>();
        for (int i = 0; i < medias.size(); i++) {
            String filePath = medias.get(i).getFileUrl();
            Path path = Paths.get(filePath);
            filePaths.add(path);
        }
        List<Resource> resources = new ArrayList<>();
        for (int i = 0; i < filePaths.size(); i++) {
            Resource resource = new FileSystemResource(filePaths.get(i));
            resources.add(resource);
        }
        if (resources == null) {
            throw new NoSuchElementException("File not found");
        }

        String output = "";
        for (int i = 0; i < resources.size(); i++) {
            output += ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resources.get(i).getFilename() + "\"")
                    .body(resources.get(i));
        }
        return output;

    }

    //add lesson to specific course
    public CourseEntity addLessonToCourse(String courseId, LessonEntity lesson, int lessonDuration) {
        CourseEntity course = courseRepository.findById(courseId);
        if (course == null) {
            throw new NoSuchElementException("Course with ID " + courseId + " not found");
        }
        course.addLesson(lesson);
        course.incrementDuration(lessonDuration);
        courseRepository.save(course);
        return course;

    }}