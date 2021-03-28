package com.gourav.springbootbasics.repository;

import com.gourav.springbootbasics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
