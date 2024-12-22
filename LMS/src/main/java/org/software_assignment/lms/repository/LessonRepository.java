package org.software_assignment.lms.repository;

import org.software_assignment.lms.entity.LessonEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class LessonRepository{
    private List<LessonEntity> lessons = new ArrayList<>(Arrays.asList(
            new LessonEntity(1, "Lesson 1", "Introduction to Java", "1"),
            new LessonEntity(2, "Lesson 2", "Data types in Java", "1"),
            new LessonEntity(10, "Lesson 2", "Data types in Java", "course_001")
    ));

    public LessonEntity findbyId(int lessonID){
        for (LessonEntity lesson : lessons) {
            if (lesson.getId()==lessonID) {
                return lesson;
            }
        }
        return null;
    }

    public List<LessonEntity> findAll(){
        return new ArrayList<>(lessons);
    }

    public boolean deleteById(int id){
        return lessons.removeIf(lesson -> lesson.getId()==id);
    }

    public LessonEntity save(LessonEntity lesson){
        if(findbyId(lesson.getId())==null){
            lessons.add(lesson);
        }else{
            deleteById(lesson.getId());
            lessons.add(lesson);
        }
        return lesson;
    }
}