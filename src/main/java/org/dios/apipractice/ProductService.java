package org.dios.apipractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void createProduct(Product product) {
        repository.save(product);
    }

    public Product getProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<Product> getProductByPrice(int price) {
        return repository.findByProductPrice(price);
    }

    public List<Product> getProductByName(String productName) {
        return repository.findByProductName(productName);
    }

    public boolean updateProduct(int id, Product newProduct) {
        if (newProduct == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        return repository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductName(newProduct.getProductName());
                    existingProduct.setProductPrice(newProduct.getProductPrice());
                    repository.save(existingProduct);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteProduct(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Product> getProductsPaginatedAndSorted(int pageNumber, int pageSize, String sortBy) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return repository.findAll(pageable);
    }

    public Page<Product> getProductsPaginated(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number cannot be negative");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable);
    }

    public List<Product> getProductsSorted(String sortBy) {
        if (sortBy == null || sortBy.trim().isEmpty()) {
            throw new IllegalArgumentException("Sort field cannot be empty");
        }
        return repository.findAll(Sort.by(sortBy));
    }

}
