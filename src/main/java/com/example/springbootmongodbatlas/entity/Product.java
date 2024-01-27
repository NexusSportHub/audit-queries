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
    private ObjectId _id;

    private String userId;
    private String apiUrl;
    private String path;
    private Boolean status;
    private java.util.Date date;
    private java.util.Date paidDate;
    private String ApiResponse;
    private String paymentMethod;
}