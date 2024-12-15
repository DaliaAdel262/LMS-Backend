package org.software_assignment.lms.entity;

public class QuizEntity {
    private int id;
    private String title;
    private String courseId;
    private String quizName;
    private int numberOfQuestions;
    public QuizEntity() {}
    public QuizEntity(int id, String title, String courseId, String quizName, int numberOfQuestions) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.quizName = quizName;
        this.numberOfQuestions = numberOfQuestions;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getQuizName() {
        return quizName;
    }
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }
    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
