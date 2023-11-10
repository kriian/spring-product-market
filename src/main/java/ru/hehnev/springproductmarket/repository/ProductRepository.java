package ru.hehnev.springproductmarket.repository;

import ru.hehnev.springproductmarket.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
    void addProduct(Product product);
    void changeProductPrice(Long id, Integer price);
    void deleteProduct(Long id);
}
