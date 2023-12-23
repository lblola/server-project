package org.example.serverproject.repositories;

import org.example.serverproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    void deleteEmployeeByName(String name);
    Optional<Employee> findEmployeeByName(String name);
}
