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
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final PassportServiceImpl passportService;
    private final Logger logger = Logger.getLogger(EmployeeController.class.getName());

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
        if(employeeService.findOne(id) == null)
            logger.log(Level.INFO, "Find employee by id is null");
        return employeeService.findOne(id);
    }

    @PostMapping
    public HttpEntity<HttpStatus> addNewEmployee(@RequestBody @Valid Employee employee,
                                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.log(Level.WARNING, "employee obj is not correct");
            throw new RuntimeException("employee obj is not correct");
        }
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    public HttpEntity<HttpStatus> deleteEmployee(@PathVariable("name") String name){
        employeeService.deleteById(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public HttpEntity<HttpStatus> patchEmployee(@PathVariable("id") int id,
                                                @RequestBody Employee employee,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.log(Level.INFO, "Employee obj is not correct");
            throw new RuntimeException("Employee obj is not correct");
        }
        employeeService.patch(id, employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/passport")
    public List<Passport> getAllPassports(){
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
