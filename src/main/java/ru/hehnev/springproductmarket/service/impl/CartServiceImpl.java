package ru.hehnev.springproductmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hehnev.springproductmarket.model.Product;
import ru.hehnev.springproductmarket.repository.CartRepository;
import ru.hehnev.springproductmarket.service.CartService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<Product> findAllProducts() {
        return cartRepository.findAllProducts();
    }

    @Override
    public boolean addProduct(Long id, String title, Integer price) {
        return cartRepository.addProduct(new Product(id, title, price));
    }
}
