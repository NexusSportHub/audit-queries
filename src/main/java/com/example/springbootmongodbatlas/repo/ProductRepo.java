package com.example.springbootmongodbatlas.repo;

import com.example.springbootmongodbatlas.entity.Product;
import org.bson.types.ObjectId;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, ObjectId> {
    // Método para buscar productos por ID de usuario
    List<Product> findByUserId(String userId);

    // Método para buscar productos por ID de usuario y estado
    List<Product> findByUserIdAndStatus(String userId, Boolean status);
}
