package org.software_assignment.lms.entity;
import java.util.List;
import java.util.Map;

public class CourseEntity {

    private String id;
    private String title;
    private String description;
    private int duration;
    private int instructorId;

    private List<LessonEntity> lessons;
    private List<AssignmentEntity> assignments;
    private List<QuizEntity> quizzes;
    private List<String> enrolledStudents;
    private Map<String, String> questionBank;

    public CourseEntity() {}

    public CourseEntity(String id, String title, String description, int duration, int instructorId,
                        List<LessonEntity> lessons, List<AssignmentEntity> assignments, List<QuizEntity> quizzes,
                        List<String> enrolledStudents, Map<String, String> questionBank) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.instructorId = instructorId;
        this.lessons = lessons;
        this.assignments = assignments;
        this.quizzes = quizzes;
        this.enrolledStudents = enrolledStudents;
        this.questionBank = questionBank;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    public List<AssignmentEntity> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentEntity> assignments) {
        this.assignments = assignments;
    }

    public List<QuizEntity> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<QuizEntity> quizzes) {
        this.quizzes = quizzes;
    }

    public List<String > getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Map<String, String> getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(Map<String, String> questionBank) {
        this.questionBank = questionBank;
    }



}
