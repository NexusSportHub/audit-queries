package com.example.springbootmongodbatlas.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import com.example.springbootmongodbatlas.entity.Product;
import com.example.springbootmongodbatlas.repo.ProductRepo;
import com.example.springbootmongodbatlas.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsByUserId(String userId) {
        return productRepo.findByUserId(userId);
    }

    @Override
    public List<Product> getProductsByUserIdAndStatus(String userId, Boolean status) {
        return productRepo.findByUserIdAndStatus(userId, status);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return productRepo.findById(new ObjectId(id)).orElse(null);
    }

    @Override
    public Product deleteProduct(int id) {
        Product product = productRepo.findById(new ObjectId(String.valueOf(id))).orElse(null);
        if (product != null) {
            productRepo.delete(product);
        }
        return product;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productVar = productRepo.findById(new ObjectId(String.valueOf(id))).orElse(null);
        if (productVar != null) {
            productVar.setUserId(product.getUserId());
            productVar.setApiUrl(product.getApiUrl());
            productVar.setPath(product.getPath());
            productVar.setStatus(product.getStatus());
            productVar.setDate(product.getDate());
            productVar.setPaidDate(product.getPaidDate());
            productVar.setApiResponse(product.getApiResponse());
            productRepo.save(productVar);
        }
        return productVar;
    }

    @Override
    public Product updateProduct(String id, Product product) {
        // Convierte la cadena 'id' a ObjectId
        ObjectId objectId = new ObjectId(id);

        // Busca el producto por el ObjectId
        Product productVar = productRepo.findById(objectId).orElse(null);

        if (productVar != null) {
            productVar.setUserId(product.getUserId());
            productVar.setApiUrl(product.getApiUrl());
            productVar.setPath(product.getPath());
            productVar.setStatus(product.getStatus());
            productVar.setDate(product.getDate());
            productVar.setPaidDate(product.getPaidDate());
            productVar.setApiResponse(product.getApiResponse());
            productVar.setPaymentMethod(product.getPaymentMethod());
            productRepo.save(productVar);
        }

        return productVar;
    }

}