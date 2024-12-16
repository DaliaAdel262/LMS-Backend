package org.software_assignment.lms.service;

import org.software_assignment.lms.entity.Admin;
import org.software_assignment.lms.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService {
    final private AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
     public void addAdmin(Admin admin) {
        if (!Validator.isValidEmail(admin.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (adminRepository.getAdminByEmail(admin.getEmail()) != null) {
            throw new IllegalArgumentException("email already exists");
        }
        if(adminRepository.getAdminById(admin.getId()) != null) {
            throw new IllegalArgumentException("id already exists");
        }
        if (!Validator.isValidPassword(admin.getPassword())) {
            throw new IllegalArgumentException("password does not match");
        }
        adminRepository.addAdmin(admin);
     }
    public void deleteAdmin(int id) {
        if (adminRepository.getAdminById(id) == null) {
            throw new IllegalArgumentException("id does not exist");
        }
        else
            adminRepository.removeAdmin(id);
    }

    public void updateAdmin(Admin admin , int id) {
        if (adminRepository.getAdminById(id) == null) {
            throw new IllegalArgumentException("id does not exist");
        }
        else
            adminRepository.updateAdminData(admin , id);
    }
    public Admin getAdminById(int id) {
        if (adminRepository.getAdminById(id) == null) {
            throw new IllegalArgumentException("Admin does not exist");
        }
        else
            return adminRepository.getAdminById(id);
    }
    public List<Admin> getAllAdmins() {
        return adminRepository.getAdmins();
    }
}
