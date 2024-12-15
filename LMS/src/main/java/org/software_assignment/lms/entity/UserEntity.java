package org.software_assignment.lms.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserEntity {

    private int id;
    private String name;
    private String birthday;
    private String email;
    private String password;

}



