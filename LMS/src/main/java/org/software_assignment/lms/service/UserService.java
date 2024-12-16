//package org.software_assignment.lms.service;
//
//import org.software_assignment.lms.entity.UserEntity;
//import org.software_assignment.lms.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.regex.Pattern;
//
//@Service
//public class UserService {
//    final  public UserRepository userRepository;
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<UserEntity> getAllUsers() {
//        return  userRepository();
//    }
//    public void addUser(UserEntity user) {
//        emailValidation(user.getEmail());
//        passwordValidation(user.getPassword());
//        userRepository.addUser(user);
//    }
//
//
//
//
//
//
//
//
//
//    private  void emailValidation(String email) {
//        Pattern p = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
//        if (p.matcher(email).matches()) {
//            throw  new IllegalArgumentException("Invalid email");
//        }
//        if (userRepository.findUserbyEmail(email))
//            throw new IllegalArgumentException("Email already exists");
//    }
//    private  void passwordValidation(String password) {
//        if (password.length() < 6) {
//            throw  new IllegalArgumentException("Password must be at least 6 characters");
//        }
//    }
//}
