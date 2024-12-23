package org.software_assignment.lms.service;

import org.software_assignment.lms.entity.UserEntity;

import java.util.regex.Pattern;

public class Validator {
    static boolean isanyEmpties(UserEntity user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return false;
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            return false;
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            return false;
        }
        return true;
    }

    static boolean isValidEmail(String email)
    {
           String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
           return Pattern.matches(EMAIL_REGEX, email);
    }
}
