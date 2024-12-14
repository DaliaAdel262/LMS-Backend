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
}
