package org.dios.apipractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
        Product product = service.getProductById(productId);
        if (product != null) {
            return product;
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @GetMapping("/price/{productPrice}")
    public List<Product> getProductByPrice(@PathVariable int productPrice) {
        List<Product> product = service.getProductByPrice(productPrice);
        if (product != null) {
            return product;
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @GetMapping("/name/{productName}")
    public List<Product> getProductByName(@PathVariable String productName) {
        List<Product> product = service.getProductByName(productName);
        if (product != null) {
            return product;
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @GetMapping("/sortBy")
    public List<Product> getProductsSorted(@RequestParam String sortBy) {
        return service.getProductsSorted(sortBy);
    }

    @GetMapping("/paginated")
    public Page<Product> getProductsPaginatedAndSorted(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String sortBy) {
        if (sortBy != null) {
            return service.getProductsPaginatedAndSorted(pageNumber, pageSize, sortBy);
        }
        return service.getProductsPaginated(pageNumber, pageSize);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable int productId, @RequestBody Product product) {
        boolean result = service.updateProduct(productId, product);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        boolean result = service.deleteProduct(productId);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
