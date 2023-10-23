package ru.hehnev.springproductmarket.service;

import ru.hehnev.springproductmarket.model.Product;

import java.util.List;

public interface CartService {
    List<Product> findAllProducts();
    boolean addProduct(Long id, String title);
}
