//package org.software_assignment.lms.controller;
//import org.software_assignment.lms.entity.Admin;
//import org.software_assignment.lms.entity.UserEntity;
//import org.software_assignment.lms.repository.CourseRepository;
//import org.software_assignment.lms.repository.UserRepository;
//import org.software_assignment.lms.service.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.springframework.web.servlet.function.ServerResponse.status;
//
//@RestController
//public class UserController {
//    UserService userService = new UserService(new UserRepository());
//    CourseRepository courseRepo;
//
////    @RequestMapping("/user/{id}")
////    public UserEntity getUser(@PathVariable("id") int userId) {
////        UserEntity user = UserRepo.findUserbyId(userId);
////        return user;
////    }
////
//    @GetMapping("/getUsers")
//    public List <UserEntity> getAllUsers() {
//        return userService.getAllUsers();
//    }
//    @PostMapping("/create/{id}")
//    public Map<String , String> createUser(@PathVariable("id") int id, @RequestBody UserEntity newUser) {
//            UserEntity adminUser = userService.userRepository.findUserbyId(id);
//            if(adminUser instanceof Admin) {
//                try {
//                    userService.addUser(newUser);
//                    return Map.of("message", "User added successfully");
//
//                }
//                catch(Exception e) {
//                    return Map.of("message", e.getMessage());
//                }
//            }
//            return Map.of("message", "User not added");
//    }
////
////    @DeleteMapping("/delete/{adminId}/{userId}")
////    public ResponseEntity<String> deleteUser(
////            @PathVariable("adminId") int adminId,
////            @PathVariable("userId") int userId) {
////        // Retrieve the admin user
////        UserEntity adminUser = UserRepo.findUserbyId(adminId);
////
////        if (adminUser == null) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin user not found.");
////        }
////
////        // Check if the requester is an admin
////        if (!(adminUser instanceof Admin)) {
////            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can delete users.");
////        }
////
////        // Retrieve the user to be deleted
////        UserEntity userToDelete = UserRepo.findUserbyId(userId);
////
////        if (userToDelete == null) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
////        }
////
////
////        // Delete the user
////        UserRepo.deleteUser(userToDelete);
////
////        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
////    }
//
//
//
//
//
//
//
//
//
//
//}
