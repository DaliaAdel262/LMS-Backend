package org.software_assignment.lms.repository;


import org.software_assignment.lms.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    private ArrayList<UserEntity> users = new ArrayList<UserEntity>();

    public void addUser(UserEntity user){
        users.add(user);
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
}
