package com.example.springbootmongodbatlas.service;

import com.example.springbootmongodbatlas.entity.Product;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ProductService {

    // Obtener todos los productos
    public List<Product> getProducts();

    // Agregar un nuevo producto
    public Product addProduct(Product product);

    // Eliminar un producto por su ID
    public Product deleteProduct(int id);

    // Actualizar un producto por su ID
    public Product updateProduct(int id, Product product);

    // Obtener productos por ID de usuario
    public List<Product> getProductsByUserId(String userId);

    // Obtener productos por ID de usuario y estado
    List<Product> getProductsByUserIdAndStatus(String userId, Boolean status);

    // Obtener productos por ID de usuario y estado

    public ResponseEntity<List<Product>> getProductsByStatus(String userId, String status);

    // Obtener un producto por su ID
    Product getProductById(String id);

    // Actualizar el estado de todos los productos del usuario y la información de
    // pago
    ResponseEntity<String> updateProductsStatusAndPaymentMethod(String userId, String paymentMethod);

}
