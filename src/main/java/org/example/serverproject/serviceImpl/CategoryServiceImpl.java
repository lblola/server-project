package org.example.serverproject.serviceImpl;

import org.example.serverproject.models.Category;
import org.example.serverproject.models.Employee;
import org.example.serverproject.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category findOne(int id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Transactional
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Transactional
    public void deleteById(int id) {
        categoryRepo.deleteById(id);
    }
}
