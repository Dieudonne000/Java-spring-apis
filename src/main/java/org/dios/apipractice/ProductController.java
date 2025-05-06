package org.dios.apipractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        service.createProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        Product product=service.getProductById(productId);
        if(product != null) {
            return product;
        }else {
            throw new RuntimeException("Product not found");
        }
    }

    @GetMapping("/price/{productPrice}")
    public List<Product> getProductByPrice(@PathVariable int productPrice) {
        List<Product> product = service.getProductByPrice(productPrice);
        if(product != null) {
            return product;
        }else{
            throw new RuntimeException("Product not found");
        }
    }

    @GetMapping("/name/{productName}")
    public List<Product> getProductByName(@PathVariable String productName) {
        List<Product> product = service.getProductByName(productName);
        if(product != null) {
            return product;
        }else{
            throw new RuntimeException("Product not found");
        }
    }
}
