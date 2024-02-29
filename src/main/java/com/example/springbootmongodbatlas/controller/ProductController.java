package com.example.springbootmongodbatlas.controller;

import com.example.springbootmongodbatlas.entity.Product;
import com.example.springbootmongodbatlas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://20.199.83.190:8082")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    // Obtener productos por ID de usuario
    @GetMapping("/user/{userId}")
    public List<Product> getProductsByUserId(@PathVariable String userId) {
        return productService.getProductsByUserId(userId);
    }

    // Obtener productos por ID de usuario y estado
    @GetMapping("/user/{userId}/{status}")
    public ResponseEntity<List<Product>> getProductsByStatus(@PathVariable String userId, @PathVariable String status) {
        return productService.getProductsByStatus(userId, status);
    }

    // Insertar un nuevo producto
    @PostMapping("/insert")
    public Product insert(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Actualizar un producto existente por ID
    @PutMapping("/update/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Actualizar el estado de todos los productos del usuario y la información de
    // pago
    @PutMapping("/paid/{userId}")
    public ResponseEntity<String> updateProductsStatusAndPaymentMethod(
            @PathVariable String userId,
            @RequestParam String paymentMethod) {
        return productService.updateProductsStatusAndPaymentMethod(userId, paymentMethod);
    }

    // Eliminar un producto por ID
    @DeleteMapping("/delete/{id}")
    public Product delete(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
