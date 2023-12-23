package org.example.serverproject.service;

import org.example.serverproject.models.Admin;
public interface AdminService {
    Admin findOne(int id);
    Admin findByName(String name);
    boolean checkAuthAdmin(Admin admin);
}
