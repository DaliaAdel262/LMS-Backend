package org.software_assignment.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Student extends UserEntity{
    //                            new Student(1, "Mariam", "2005", "mariameid33@gmail.com", "ra5"),
    public Student(
            int id,
            String name,
            String birthDay,
            String email,
            String password,
            String role
    ) {
        super(id , name, birthDay, email, password, role );
    }
    public Student(){}
}

