package com.example.springbootmongodbatlas.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Obtener productos por ID de usuario y estado
    @Override
    public ResponseEntity<List<Product>> getProductsByStatus(String userId, String status) {
        try {
            boolean statusValue = Boolean.parseBoolean(status); // Convierte el valor de la cadena a booleano
            List<Product> products = getProductsByUserIdAndStatus(userId, statusValue);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            // Manejar una entrada no válida para el estado (debería ser "true" o "false")
            return ResponseEntity.badRequest().build();
        }
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

    // Actualizar el estado de todos los productos del usuario y la información de
    // pago
    @Override
    public ResponseEntity<String> updateProductsStatusAndPaymentMethod(String userId, String paymentMethod) {
        try {
            List<Product> products = productRepo.findByUserIdAndStatus(userId, false);
            if (products.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            for (Product product : products) {
                product.setStatus(true);
                product.setPaidDate(new java.util.Date());
                product.setPaymentMethod(paymentMethod);
                productRepo.save(product);
            }

            return ResponseEntity.ok("Se actualizaron correctamente los productos del usuario con ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar los productos del usuario con ID: " + userId);
        }
    }

}
