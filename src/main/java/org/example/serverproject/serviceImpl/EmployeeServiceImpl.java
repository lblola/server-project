package org.example.serverproject.serviceImpl;

import org.example.serverproject.models.Employee;
import org.example.serverproject.repositories.EmployeeRepo;
import org.example.serverproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Transactional
    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Transactional
    @Override
    public Employee findOne(int id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(String name) {
        employeeRepo.findEmployeeByName(name).ifPresent(employee -> employeeRepo.deleteEmployeeByName(name));
    }

    @Transactional
    @Override
    public void patch(int id, Employee employee) {
        Employee curEmployee = employeeRepo.findById(id).orElse(null);

        if(curEmployee != null && employee.getName() != null){
            curEmployee.setName(employee.getName());
            curEmployee.setSalary(employee.getSalary());
            employeeRepo.save(curEmployee);
        }
    }
}
