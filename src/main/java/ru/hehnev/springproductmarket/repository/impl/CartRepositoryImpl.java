package ru.hehnev.springproductmarket.repository.impl;

import org.springframework.stereotype.Repository;
import ru.hehnev.springproductmarket.model.Product;
import ru.hehnev.springproductmarket.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAllProducts() {
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        return products.add(product);
    }
}
