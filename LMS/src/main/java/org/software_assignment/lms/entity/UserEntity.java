package org.software_assignment.lms.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Setter
@Getter
public class UserEntity {
    public UserEntity() {}
    public UserEntity(
            int id,
            String name,
             String birthday,
            String email,
            String password,
            String role
    ) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }
    private int id;
    private String name;
    private String birthday;
    private String email;
    private String password;
    private String role;
}



