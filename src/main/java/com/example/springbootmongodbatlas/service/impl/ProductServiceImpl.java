package com.example.springbootmongodbatlas.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootmongodbatlas.entity.Product;
import com.example.springbootmongodbatlas.repo.ProductRepo;
import com.example.springbootmongodbatlas.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    // Obtener todos los productos
    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    // Obtener productos por ID de usuario
    @Override
    public List<Product> getProductsByUserId(String userId) {
        return productRepo.findByUserId(userId);
    }

    // Obtener productos por ID de usuario y estado
    @Override
    public List<Product> getProductsByUserIdAndStatus(String userId, Boolean status) {
        return productRepo.findByUserIdAndStatus(userId, status);
    }

    // Agregar un nuevo producto
    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    // Obtener un producto por su ID
    @Override
    public Product getProductById(String id) {
        return productRepo.findById(new ObjectId(id)).orElse(null);
    }

    // Eliminar un producto por su ID
    @Override
    public Product deleteProduct(int id) {
        Product product = productRepo.findById(new ObjectId(String.valueOf(id))).orElse(null);
        if (product != null) {
            productRepo.delete(product);
        }
        return product;
    }

    // Actualizar un producto por su ID
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

    // Actualizar la información de pago de un producto por su ID
    @Override
    public Product updateProduct(String id, Product product) {
        ObjectId objectId = new ObjectId(id); // Convertir la cadena 'id' a ObjectId
        Product productVar = productRepo.findById(objectId).orElse(null); // Buscar el producto por su ObjectId
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
