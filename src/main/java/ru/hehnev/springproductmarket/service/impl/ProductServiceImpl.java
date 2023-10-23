package ru.hehnev.springproductmarket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hehnev.springproductmarket.model.Product;
import ru.hehnev.springproductmarket.repository.ProductRepository;
import ru.hehnev.springproductmarket.service.ProductService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
