package org.software_assignment.lms.entity;




public class Admin extends UserEntity{
    public UserEntity createUser(int id,String name, String birthday, String email, String password){
        UserEntity user = new UserEntity(id,name,birthday,email,password);
        return user;
    }


}
