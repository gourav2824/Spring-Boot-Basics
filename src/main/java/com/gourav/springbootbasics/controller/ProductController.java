package com.gourav.springbootbasics.controller;

import com.gourav.springbootbasics.model.Product;
import com.gourav.springbootbasics.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products")
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return repository.findById(id).get();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        repository.deleteById(id);
    }
}
