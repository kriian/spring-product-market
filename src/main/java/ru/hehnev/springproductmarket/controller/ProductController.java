package ru.hehnev.springproductmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hehnev.springproductmarket.model.Product;
import ru.hehnev.springproductmarket.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public String getTest(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product_page";
    }

    @GetMapping("/all")
    public String getTest(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product_info_page";
    }
}
