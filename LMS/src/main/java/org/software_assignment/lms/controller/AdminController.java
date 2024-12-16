package org.software_assignment.lms.controller;

import org.software_assignment.lms.entity.Admin;
import org.software_assignment.lms.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("admin/add")
    public ResponseEntity<Map<String, String>> addAdmin(@RequestBody Admin admin) {
        try {
            if (admin == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("status", "error", "message", "User parameters are required"));
            }
            adminService.addAdmin(admin);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("status", "success", "message", "Admin added successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/admin/{id}/delete")
    public ResponseEntity<Map<String, String>> deleteAdmin(@PathVariable("id") int id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok().body(Map.of("status", "success", "message", "Admin deleted successfully"));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @GetMapping("/admin/all")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PatchMapping("/admin/{id}/update")
    public ResponseEntity<Map<String, String>> updateAdmin(@PathVariable("id") int id, @RequestBody Admin admin) {
        try {
            if (admin == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Map.of("status", "error", "message", "User parameters are required")
                );
            }
            adminService.updateAdmin(admin, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    Map.of("status", "success", "message", "Admin updated successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("status", "error", "message", e.getMessage())
            );
        }
    }
    
    @GetMapping("/admin/{id}")
    public ResponseEntity<Map<String , Admin>> getAdminById(@PathVariable("id") int id) {
        try {
            Admin admin = adminService.getAdminById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    Map.of("data", admin)
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("Error" , new Admin())
            );
        }
    }
}
