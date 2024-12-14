package org.software_assignment.lms.entity;

public class LessonEntity {
    private int id;
    private String title;
    private String content;
    private String courseId;
    public LessonEntity() {}
    public LessonEntity(int id, String title, String content, String courseId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.courseId = courseId;
    }
}
