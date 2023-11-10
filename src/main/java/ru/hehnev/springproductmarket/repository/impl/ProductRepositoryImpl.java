package ru.hehnev.springproductmarket.repository.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.hehnev.springproductmarket.model.Product;
import ru.hehnev.springproductmarket.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>() {{
           add(new Product(1L, "Молоко", 71));
           add(new Product(2L, "Хлеб", 52));
           add(new Product(3L, "Сыр", 800));
           add(new Product(4L, "Каша", 100));
        }};
    }
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such product. id = " + id));
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void changeProductPrice(Long id, Integer price) {
        Product product = findById(id);
        product.setPrice(product.getPrice() + price);
    }

    @Override
    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
