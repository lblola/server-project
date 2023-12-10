package org.example.serverproject.service;

import org.example.serverproject.models.Employee;
import org.example.serverproject.models.Passport;

import java.util.List;

public interface PassportService {
    List<Passport> findAll();
    Passport findOne(int id);
    void save(Passport passport);
    void deleteById(int id);
}
