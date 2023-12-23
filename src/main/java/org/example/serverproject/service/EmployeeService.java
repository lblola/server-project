package org.example.serverproject.service;

import org.example.serverproject.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findOne(int id);
    void save(Employee employee);
    void deleteById(String name);
    void patch(int id, Employee employee);
}
