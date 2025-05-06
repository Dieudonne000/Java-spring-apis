package org.dios.apipractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public void createProduct(Product product){
        repository.save(product);
    }

//    public Optional<Product> getProduct(int productId){
//        Optional<Product> product = repository.findById(productId);
//        if(!product.isPresent()){
//            return Optional.empty();
//        }
//        return product;
//    }
//
    public Product getProductById(int id){
        Optional<Product> product = repository.findById(id);
        return product.orElse(null);
    }

    public List<Product> getProductByPrice(int price){
        List<Product> product = repository.findByProductPrice(price);
        if(product != null) return product;
        return null;
    }

    public List<Product> getProductByName(String productName){
        List<Product> product = repository.findByProductName(productName);
        if(product != null) return product;
        return null;
    }


}
