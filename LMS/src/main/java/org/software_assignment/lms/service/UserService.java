package org.software_assignment.lms.service;

import componnet.JwtGenerator;
import org.software_assignment.lms.entity.UserEntity;
import org.software_assignment.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private JwtGenerator jwtGenerator = new JwtGenerator();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String login(String email, String password) {
        UserEntity user = userRepository.findUserbyEmail(email);
        if (user == null) {
            throw new RuntimeException("email or password wrong");
        }
        else
        {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new RuntimeException("email or password wrong");
            }
            return jwtGenerator.generateToken(user.getRole());
        }
    }

    public void register(UserEntity user) {
        UserEntity userEntity = userRepository.findUserbyEmail(user.getEmail());
        if (userEntity != null) {
            throw new RuntimeException("user already exists");
        }
        if(!Validator.isanyEmpties(user))
        {
            throw new RuntimeException("some required fields are empty");
        }
        if(!Validator.isValidEmail(user.getEmail()))
        {
            throw new RuntimeException("email is valid");
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(user.getRole().toUpperCase());
            userRepository.addUser(user);
        }
    }

    public void  deleteUser(int id)
    {
        UserEntity user = userRepository.findUserbyId(id);
        if(user == null)
        {
            throw new RuntimeException("user not found");
        }
        userRepository.deleteUser(user);
    }

    public UserEntity findUserbyId(int userId) {
        UserEntity user = userRepository.findUserbyId(userId);
        if(user == null)
            throw new RuntimeException("user not found");
        return user;
    }
}
