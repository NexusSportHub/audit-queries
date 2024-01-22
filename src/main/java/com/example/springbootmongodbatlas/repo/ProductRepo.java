package com.example.springbootmongodbatlas.repo;

import com.example.springbootmongodbatlas.entity.Product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, Integer> {
    List<Product> findByUserId(String userId);

    List<Product> findByUserIdAndStatus(String userId, Boolean status);

}
