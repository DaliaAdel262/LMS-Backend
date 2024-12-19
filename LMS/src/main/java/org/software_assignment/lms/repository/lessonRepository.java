package org.software_assignment.lms.repository;

import java.util.*;

import org.software_assignment.lms.entity.LessonEntity;
import org.springframework.stereotype.Repository;

@Repository
public class LessonRepository{
    private List<LessonEntity> lessons = new ArrayList<>();

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
