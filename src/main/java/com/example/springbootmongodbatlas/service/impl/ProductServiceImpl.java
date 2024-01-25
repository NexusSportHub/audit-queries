package com.example.springbootmongodbatlas.service.impl;

import com.example.springbootmongodbatlas.entity.Product;
import com.example.springbootmongodbatlas.repo.ProductRepo;
import com.example.springbootmongodbatlas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        productVar.setApiResponse(product.getApiResponse());
        productRepo.save(productVar);
        return productVar;

    }

    @Override
    public void updateStatusAndDateForUser(List<Product> products) {
        // Filtra solo los productos que han cambiado su estado a true
        List<Product> updatedProducts = products.stream()
                .filter(product -> product.getStatus() && product.getPaidDate() != null)
                .collect(Collectors.toList());

        // Guarda los cambios en la base de datos solo para los productos que cumplen
        // con el filtro
        if (!updatedProducts.isEmpty()) {
            productRepo.saveAll(updatedProducts);
        }
    }
}
