package org.example.serverproject.restControllers;


import jakarta.validation.Valid;
import org.example.serverproject.models.Employee;
import org.example.serverproject.models.Passport;
import org.example.serverproject.serviceImpl.EmployeeServiceImpl;

import org.example.serverproject.serviceImpl.PassportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final PassportServiceImpl passportService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService, PassportServiceImpl passportService) {
        this.employeeService = employeeService;
        this.passportService = passportService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") int id){
        return employeeService.findOne(id);
    }

    @PostMapping
    public HttpEntity<HttpStatus> addNewEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<HttpStatus> deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/passport")
    public List<Passport> getAllPasports(){
        return passportService.findAll();
    }

    @GetMapping("/{id}/passport")
    public Passport getPassport(@PathVariable("id") int id){
        Passport passport = employeeService.findOne(id).getPassport();
        return passport;
    }

    @PostMapping("/passport")
    public HttpEntity<HttpStatus> addNewPassport(@RequestBody @Valid Passport passport, BindingResult bindingResult){
        passportService.save(passport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/passport")
    public HttpEntity<HttpStatus> deletePassport(@PathVariable("id") int id){
        Passport passport = employeeService.findOne(id).getPassport();
        passportService.deleteById(passport.getPassportId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
