package ru.hehnev.springproductmarket.repository;

import ru.hehnev.springproductmarket.model.Product;

import java.util.List;

public interface CartRepository {
    List<Product> findAllProducts();
    boolean addProduct(Product product);
}
