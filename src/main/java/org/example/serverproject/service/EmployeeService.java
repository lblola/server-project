package org.example.serverproject.service;

import org.example.serverproject.models.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findOne(int id);
    void save(Employee employee);

    void deleteById(int id);
}
