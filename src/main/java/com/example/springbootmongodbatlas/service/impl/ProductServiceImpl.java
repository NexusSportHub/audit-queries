<<<<<<< HEAD
package com.example.springbootmongodbatlas.service.impl;

import com.example.springbootmongodbatlas.entity.Product;
import com.example.springbootmongodbatlas.repo.ProductRepo;
import com.example.springbootmongodbatlas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Product deleteProduct(int id) {
        Product product = productRepo.findById(id).get();
        productRepo.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productVar = productRepo.findById(id).get();
        productVar.setUserId(product.getUserId());
        productVar.setApiUrl(product.getApiUrl());
        productVar.setPath(product.getPath());
        productVar.setStatus(product.getStatus());
        productVar.setDate(product.getDate());
        productVar.setPaidDate(product.getPaidDate());
        productRepo.save(productVar);
        return productVar;

    }
}
=======
package com.example.springbootmongodbatlas.service.impl;

import com.example.springbootmongodbatlas.entity.Product;
import com.example.springbootmongodbatlas.repo.ProductRepo;
import com.example.springbootmongodbatlas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsByUserId(Long userId) {
        return productRepo.findByUserId(userId);
    }

    @Override
    public List<Product> getProductsByUserIdAndStatus(Long userId, Boolean status) {
        return productRepo.findByUserIdAndStatus(userId, status);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product deleteProduct(int id) {
        Product product = productRepo.findById(id).get();
        productRepo.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productVar = productRepo.findById(id).get();
        productVar.setRequestId(product.getRequestId());
        productVar.setUserId(product.getUserId());
        productVar.setApiUrl(product.getApiUrl());
        productVar.setPath(product.getPath());
        productVar.setStatus(product.getStatus());
        productVar.setDate(product.getDate());
        productVar.setPaidDate(product.getPaidDate());
        productRepo.save(productVar);
        return productVar;

    }
}
>>>>>>> master
