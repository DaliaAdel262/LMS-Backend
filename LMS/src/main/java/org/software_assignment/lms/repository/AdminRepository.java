package org.software_assignment.lms.repository;

import lombok.Getter;
import org.software_assignment.lms.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Getter
public class AdminRepository {
    private final ArrayList<Admin> admins = new ArrayList<>();

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void removeAdmin(int adminId) {
        admins.removeIf(admin -> admin.getId() == adminId);
    }

    public void updateAdminData(Admin admin , int id) {
        for (Admin adminObject : admins) {
            if (adminObject.getId() == id) {
                adminObject.setName(admin.getName());
                adminObject.setPassword(admin.getPassword());
                adminObject.setBirthday(admin.getBirthday());
                adminObject.setEmail(admin.getEmail());
            }
        }
    }

    public Admin getAdminById(int id)
    {
        for (Admin admin : admins) {
            if (admin.getId() == id) {
                return admin;
            }
        }
        return null;
    }

    public Admin getAdminByEmail(String email) {
        for (Admin admin : admins) {
            if (admin.getEmail().trim().equalsIgnoreCase(email.trim())) {
                return admin;
            }
        }
        return null;
    }

}
