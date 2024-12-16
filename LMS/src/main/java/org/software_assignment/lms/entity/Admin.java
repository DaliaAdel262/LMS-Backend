package org.software_assignment.lms.entity;

public class Admin extends UserEntity {
    public Admin() {}
    public Admin(int id , String name , String email , String password , String birthDay) {
        super(id, name, birthDay, email, password);
    }
}
