package org.dios.apipractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT * from Product p where p.productPrice = productPrice")
    List<Product> findByProductPrice(@Param("productPrice") int price);

    @Query("SELECT p from Product p where p.productName =:productName")
    List<Product> findByProductName(@Param("productName") String productName);
}
