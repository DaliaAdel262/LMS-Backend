package org.software_assignment.lms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseEntity {

    private String id;
    private String title;
    private String description;
    private int duration;
    private int instructorId;
    private List<MediaFile> mediaFiles = new ArrayList<>();

    private List<LessonEntity> lessons;
    private List<AssignmentEntity> assignments= new ArrayList<>();
    private List<QuizEntity> quizzes;
    private List<UserEntity> enrolledStudents = new ArrayList<>();

    private Map<String, String> questionBank;

    public CourseEntity() {}

    public CourseEntity(String id, String title, String description, int duration, int instructorId,
                        List<LessonEntity> lessons, List<AssignmentEntity> assignments, List<QuizEntity> quizzes,
                        List<UserEntity> enrolledStudents, Map<String, String> questionBank) {
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

    public List<UserEntity > getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<UserEntity> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Map<String, String> getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(Map<String, String> questionBank) {
        this.questionBank = questionBank;
    }
    public void EnrollCourse(int studentId){
    }

    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    public void addMediaFiles(MediaFile mediaFile){
        this.mediaFiles.add(mediaFile);
    }

    public void enrollStudet(UserEntity student) {
        enrolledStudents.add(student);

    }

    public void addAssigment(AssignmentEntity assignment) {
        int  id =assignment.getAssignmentID() ;
        boolean found=false;
        int i;
        AssignmentEntity tempAssigment=null;
        for(i=0;i<assignments.size();i++){
             tempAssigment = assignments.get(i);
            if(tempAssigment.getAssignmentID() == assignment.getAssignmentID()){
                 found=true;
                 break;
            }
        }

        if(!found){
            System.out.println("NOT ----- > Remvoing assigment");
            assignments.add(assignment);
        }
        else {
            System.out.println("Remvoing assigment");
            assignments.remove(tempAssigment);
            assignments.add(assignment);
        }
    }

    //array list of lessons
    public void addLesson(LessonEntity lesson) {
        if (this.lessons == null) {
            this.lessons = new ArrayList<>();
        }
        this.lessons.add(lesson);
    }


    public void incrementDuration(int duration) {
        this.duration += duration;
    }
}

