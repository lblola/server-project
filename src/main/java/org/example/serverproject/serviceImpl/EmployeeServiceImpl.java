package org.example.serverproject.serviceImpl;

import org.example.serverproject.models.Employee;
import org.example.serverproject.repositories.EmployeeRepo;
import org.example.serverproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee findOne(int id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeRepo.deleteById(id);
    }
}
