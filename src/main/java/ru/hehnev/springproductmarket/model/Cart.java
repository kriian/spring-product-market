package ru.hehnev.springproductmarket.model;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private List<Product> products;
}
