package org.software_assignment.lms.repository;

import java.util.*;

import org.software_assignment.lms.entity.LessonEntity;
import org.springframework.stereotype.Repository;

@Repository
public class LessonRepository{
    private List<LessonEntity> lessons = new ArrayList<>(Arrays.asList(
            new LessonEntity(1, "Lesson 1", "Introduction to Java", "1"),
            new LessonEntity(2, "Lesson 2", "Data types in Java", "1")
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