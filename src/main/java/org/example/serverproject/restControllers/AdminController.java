package org.example.serverproject.restControllers;

import org.example.serverproject.models.Admin;
import org.example.serverproject.serviceImpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final Logger logger = Logger.getLogger(AdminController.class.getName());
    private final AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable("id")int id){
        if(adminService.findOne(id) == null){
            logger.log(Level.INFO, "find Admin is null");
        }
        return adminService.findOne(id);
    }

    @GetMapping("/find/{name}")
    public Admin getAdminByName(@PathVariable("name")String name){
        if(adminService.findByName(name) == null){
            logger.log(Level.INFO, "find Admin by name is null");
        }
        return adminService.findByName(name);
    }

    @PostMapping("/auth")
    public Boolean getResultAuthAdmin(@RequestBody Admin admin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.log(Level.WARNING, "json object admin is not correct");
        }
        return adminService.checkAuthAdmin(admin);
    }
}
