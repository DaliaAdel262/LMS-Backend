package org.software_assignment.lms.controller;
import org.software_assignment.lms.entity.UserEntity;
import org.software_assignment.lms.repository.CourseRepository;
import org.software_assignment.lms.repository.UserRepository;
import org.software_assignment.lms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController()
public class UserController {
    private final UserService userService;
    UserRepository UserRepo = new UserRepository();
    UserService UserService = new UserService();
    CourseRepository courseRepo;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/auth/user/{id}")
    public UserEntity getUser(@PathVariable("id") int userId) {
        try {
            return userService.findUserbyId(userId);
        }
        catch (Exception e) {
            return new UserEntity();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/api/auth/register")
    public String createUser(@RequestBody UserEntity newUser) {
        try {
             userService.register(newUser);
             return "User registered successfully";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/api/auth/delete/{userID}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("userID") int userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody UserEntity userEntity) {
        try {
            String token= userService.login(userEntity.getEmail() , userEntity.getPassword());
            return ResponseEntity.ok(Map.of("token", token));

        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/api/auth/users/update")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity userEntity) {
        try {
            userService.updateUser(userEntity);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}


