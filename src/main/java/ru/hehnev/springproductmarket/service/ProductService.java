package ru.hehnev.springproductmarket.service;

import ru.hehnev.springproductmarket.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    void addProduct(Product product);
    void changeProductPrice(Long id, Integer price);
    void deleteProduct(Long id);
}
