package com.example.springbootmongodbatlas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "requests")
public class Product {

    @Id
    private ObjectId _id; // Identificador único del producto

    private String userId; // ID del usuario asociado al producto
    private String apiUrl; // URL de la API utilizada
    private String path; // Ruta dentro de la API
    private Boolean status; // Estado del producto (activo/inactivo)
    private java.util.Date date; // Fecha de creación del producto
    private java.util.Date paidDate; // Fecha de pago del producto
    private String ApiResponse; // Respuesta de la API
    private String paymentMethod; // Método de pago utilizado para el producto
}
