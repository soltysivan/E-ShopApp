package org.shop.dao.repository;

import org.shop.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long aLong);

    Product findProductById(Long id);
}
