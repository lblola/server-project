package org.example.serverproject.serviceImpl;

import org.example.serverproject.models.Employee;
import org.example.serverproject.models.Product;
import org.example.serverproject.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl {
    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }


    public Product findOne(int id) {
        return productRepo.findById(id).orElse(null);
    }


    @Transactional
    public void save(Product product) {
        productRepo.save(product);
    }


    @Transactional
    public void deleteById(int id) {
        productRepo.deleteById(id);
    }
}
