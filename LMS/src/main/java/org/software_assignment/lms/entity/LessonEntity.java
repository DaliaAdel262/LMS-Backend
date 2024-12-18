package org.software_assignment.lms.entity;

import java.util.*;

public class LessonEntity {
    private int id;
    private String title;
    private String content;
    private String courseId;
    private List<Student> studentsAttended;
    public LessonEntity() {}
    public LessonEntity(int id, String title, String content, String courseId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.courseId = courseId;
        this.studentsAttended = new ArrayList<>();
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
    public String getContent() {
        return content;

    }
    public void setContent(String content) {
        this.content = content;

    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
