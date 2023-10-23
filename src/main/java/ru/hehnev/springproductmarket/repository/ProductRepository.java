package ru.hehnev.springproductmarket.repository;

import ru.hehnev.springproductmarket.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
}
