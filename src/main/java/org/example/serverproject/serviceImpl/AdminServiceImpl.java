package org.example.serverproject.serviceImpl;

import org.example.serverproject.models.Admin;
import org.example.serverproject.models.Client;
import org.example.serverproject.repositories.AdminRepo;
import org.example.serverproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin findOne(int id) {
        return adminRepo.findById(id).orElse(null);
    }

    @Override
    public Admin findByName(String name) {
        return adminRepo.findAdminByName(name).orElse(null);
    }

    @Override
    public boolean checkAuthAdmin(Admin admin) {
        Admin currAdmin = findByName(admin.getName());
        if (currAdmin != null) {
            return currAdmin.getPassword().equals(admin.getPassword());
        }
        return false;
    }
}
