package org.example.serverproject.serviceImpl;


import org.example.serverproject.models.Passport;
import org.example.serverproject.repositories.PassportRepo;
import org.example.serverproject.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PassportServiceImpl implements PassportService {
    private PassportRepo passportRepo;

    @Autowired
    public PassportServiceImpl(PassportRepo passportRepo) {
        this.passportRepo = passportRepo;
    }

    @Override
    public List<Passport> findAll() {
        return passportRepo.findAll();
    }

    @Override
    public Passport findOne(int id) {
        return passportRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Passport passport) {
        passportRepo.save(passport);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        passportRepo.deleteById(id);
    }
}
