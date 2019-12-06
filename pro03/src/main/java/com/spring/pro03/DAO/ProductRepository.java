package com.spring.pro03.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pro03.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
