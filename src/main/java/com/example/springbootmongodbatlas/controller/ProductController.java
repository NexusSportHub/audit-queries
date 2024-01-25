package com.example.springbootmongodbatlas.controller;

import com.example.springbootmongodbatlas.entity.Product;
import com.example.springbootmongodbatlas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/user/{userId}")
    public List<Product> getProductsByUserId(@PathVariable String userId) {
        return productService.getProductsByUserId(userId);
    }

    @GetMapping("/user/{userId}/{status}")
    public ResponseEntity<List<Product>> getProductsByStatus(@PathVariable String userId, @PathVariable String status) {
        try {
            boolean statusValue = Boolean.parseBoolean(status); // Convierte el valor de la cadena a booleano
            List<Product> products = productService.getProductsByUserIdAndStatus(userId, statusValue);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            // Manejar una entrada no válida para el estado (debería ser "true" o "false")
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/insert")
    public Product insert(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/user/{userId}/paid")
    public ResponseEntity<String> updateStatusAndDateForUser(@PathVariable String userId) {
        try {
            // Obtén la lista de productos para el userId dado
            List<Product> products = productService.getProductsByUserId(userId);

            // Actualiza el estado y la fecha actual de los productos que cambian a true por
            // primera vez
            for (Product product : products) {
                if (!product.getStatus()) {
                    product.setStatus(true);
                    product.setPaidDate(new java.util.Date());
                }
            }

            // Guarda los cambios en la base de datos
            productService.updateStatusAndDateForUser(products);

            return ResponseEntity
                    .ok("Estado y fecha actualizados correctamente para los productos del usuario con ID: " + userId);
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante el proceso
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el estado y la fecha para el usuario con ID: " + userId);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Product delete(@PathVariable int id) {

        return productService.deleteProduct(id);
    }
}
