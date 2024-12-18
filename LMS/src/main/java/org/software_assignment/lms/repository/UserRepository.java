package org.software_assignment.lms.repository;


import componnet.UserTypes;
import org.software_assignment.lms.entity.Admin;
import org.software_assignment.lms.entity.Instructor;
import org.software_assignment.lms.entity.Student;
import org.software_assignment.lms.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    private ArrayList<UserEntity> users = new ArrayList<UserEntity>();

    public void addUser(UserEntity user){
        if(user.getRole().equals("ADMIN"))
        {
            Admin admin = new Admin();
            admin.setRole(user.getRole());
            admin.setPassword(user.getPassword());
            admin.setId(user.getId());
            admin.setName(user.getName());
            admin.setEmail(user.getEmail());
            admin.setBirthday(user.getBirthday());
            users.add(admin);
        }
        else if (user.getRole().equals("STUDENT"))
        {
            System.out.println("Student added");
            Student student = new Student();
            student.setRole(user.getRole());
            student.setPassword(user.getPassword());
            student.setId(user.getId());
            student.setName(user.getName());
            student.setEmail(user.getEmail());
            student.setBirthday(user.getBirthday());
            users.add(student);
        }
        else {
            Instructor instructor = new Instructor();
            instructor.setRole(user.getRole());
            instructor.setPassword(user.getPassword());
            instructor.setId(user.getId());
            instructor.setName(user.getName());
            instructor.setEmail(user.getEmail());
            instructor.setBirthday(user.getBirthday());
            users.add(instructor);
        }
    }

    public void deleteUser(UserEntity user){
        users.remove(user);
    }

    void updateName(int id, String name){
        UserEntity updateUser = findUserbyId(id);
        updateUser.setName(name);
    }

    void updateBirthDay(int id, String birthday){
        UserEntity updateUser = findUserbyId(id);
        updateUser.setBirthday(birthday);
    }

    void updateEmail(int id, String email){
        UserEntity updateUser = findUserbyId(id);
        updateUser.setEmail(email);
    }

    void updatePassword(int id, String password){
        UserEntity updateUser = findUserbyId(id);
        updateUser.setPassword(password);
    }


    public UserEntity findUserbyId(int id){
        for(int i =0;i<users.size();i++) {
            if(users.get(i).getId()==id)
                return users.get(i);
        }
        return null;
    }
    public  UserEntity findUserbyEmail(String email){
        for(UserEntity user : users) {
            if (user.getEmail().toLowerCase().trim().equals(email.toLowerCase().trim())) {
                return user;
            }
        }
        return null;
    }
}
