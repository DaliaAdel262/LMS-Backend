package org.software_assignment.lms.entity;

import java.util.HashMap;
import java.util.Map;

public class QuizEntity {
    private int id;
    private String title;
    private String courseId;
    private int numberOfQuestions;
    private Map<String, String> ModelAnswer;
    private Map<Integer, Map<String ,String >> StudentAnswers=new HashMap<>();

    private Map <Integer,Integer> studentGrades = new HashMap<>();
    public QuizEntity() {}
    public QuizEntity(int id, String title, String courseId, int numberOfQuestions, Map<String, String> modelAnswer) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.numberOfQuestions = numberOfQuestions;
        this.ModelAnswer = modelAnswer; // Set model answers
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

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }
    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
    public Map<String, String> getModelAnswer() {
        return ModelAnswer;
    }

    public void setModelAnswer(Map<String, String> modelAnswer) {
        this.ModelAnswer = modelAnswer;
    }

    public Map<Integer, Map<String, String>> getStudentAnswers() {
        return StudentAnswers;
    }

    public void setStudentAnswers(Map<Integer, Map<String, String>> studentAnswers) {
        this.StudentAnswers = studentAnswers;
    }
    public void addStudentAnswer(int studtId, Map<String, String> answer) {
        StudentAnswers.put(studtId,answer);

    }

    public void setStudentGrades(Map<Integer, Integer> studentGrades) {
        this.studentGrades = studentGrades;
    }

    public Map<Integer, Integer> getStudentGrades() {
        return studentGrades;
    }

    public void addstudentGrades(int studentId, int grade){
        if(studentGrades.containsKey(studentId))
            studentGrades.replace(studentId,grade);
        else studentGrades.put(studentId,grade);
    }
}
