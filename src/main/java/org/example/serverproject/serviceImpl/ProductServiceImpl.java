package org.example.serverproject.serviceImpl;

import org.example.serverproject.models.Client;
import org.example.serverproject.models.Product;
import org.example.serverproject.repositories.ClientRepo;
import org.example.serverproject.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl {
    private final ProductRepo productRepo;
    private final ClientRepo clientRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, ClientRepo clientRepo) {
        this.productRepo = productRepo;
        this.clientRepo = clientRepo;
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
    public void deleteById(int clientId, int productId) {
        Client client = clientRepo.findById(clientId).orElse(null);
        Product product = productRepo.findById(productId).orElse(null);
        if(client != null && product != null){
            List<Product> products = client.getProductList();
            products.remove(product);
            client.setProductList(products);
        }
    }

    @Transactional
    public void saveProductForClient(int clientId, int productId){
        Client client = clientRepo.findById(clientId).orElse(null);
        Product product = productRepo.findById(productId).orElse(null);
        if(client != null){
            List<Product> products = client.getProductList();
            products.add(product);
            client.setProductList(products);
        }
    }
}
