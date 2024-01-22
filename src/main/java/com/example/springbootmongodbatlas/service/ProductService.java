<<<<<<< HEAD
package com.example.springbootmongodbatlas.service;

import com.example.springbootmongodbatlas.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts();

    public Product addProduct(Product product);

    public Product deleteProduct(int id);

    public Product updateProduct(int id, Product product);

    public List<Product> getProductsByUserId(String userId);

    List<Product> getProductsByUserIdAndStatus(String userId, Boolean status);

}
=======
package com.example.springbootmongodbatlas.service;

import com.example.springbootmongodbatlas.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts();

    public Product addProduct(Product product);

    public Product deleteProduct(int id);

    public Product updateProduct(int id, Product product);

    public List<Product> getProductsByUserId(Long userId);

    List<Product> getProductsByUserIdAndStatus(Long userId, Boolean status);

}
>>>>>>> master
