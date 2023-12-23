package org.example.serverproject.restControllers;

import jakarta.validation.Valid;
import org.example.serverproject.models.Category;
import org.example.serverproject.models.Product;
import org.example.serverproject.serviceImpl.CategoryServiceImpl;
import org.example.serverproject.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;

    @Autowired
    public ProductController(ProductServiceImpl productService, CategoryServiceImpl categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") int id){
        return productService.findOne(id);
    }

    @PostMapping
    public HttpEntity<HttpStatus> addNewProduct(@RequestBody @Valid Product product, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*@DeleteMapping("/{id}")
    public HttpEntity<HttpStatus> deleteProduct(@PathVariable("id") int id){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @GetMapping("/category")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

   /* @GetMapping("/{id}/category")
    public Category getCategory(@PathVariable("id") int id){
        Category category = productService.findOne(id).getCategory();
        return categoryService.findOne(category.getCategory_id());
    }*/

    @PostMapping("/category")
    public HttpEntity<HttpStatus> addNewCategory(@RequestBody Category category){
        categoryService.save(category);
        return ResponseEntity.ok(HttpStatus.OK);
    }

   /* @DeleteMapping("/{id}/category")
    public HttpEntity<HttpStatus> deleteCategory(@PathVariable("id") int id){
        Category category = productService.findOne(id).getCategory();
        categoryService.deleteById(category.getCategory_id());
        return ResponseEntity.ok(HttpStatus.OK);
    }*/


}
