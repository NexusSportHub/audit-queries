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

    // Nuevo endpoint para actualizar la información de pago
    @PutMapping("/paid/{id}")
    public ResponseEntity<String> actualizarInformacionDePago(
            @PathVariable String id,
            @RequestParam String metodoDePago) {
        try {
            // Obtener el producto por ID
            Product producto = productService.getProductById(id);

            // Verificar si el producto existe
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ningún producto con la ID: " + id);
            }

            // Actualizar la información de pago, estado y fecha
            producto.setPaymentMethod(metodoDePago);
            producto.setStatus(true);
            producto.setPaidDate(new java.util.Date());

            // Guardar los cambios en la base de datos
            productService.updateProduct(id, producto);

            return ResponseEntity.ok("Información de pago actualizada correctamente para el producto con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la información de pago para el producto con ID: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Product delete(@PathVariable int id) {

        return productService.deleteProduct(id);
    }
}