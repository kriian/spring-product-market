package ru.hehnev.springproductmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hehnev.springproductmarket.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping()
    public String findAllProducts(Model model) {
        model.addAttribute("cart", cartService.findAllProducts());
        return "cart_page";
    }

    @GetMapping("/add")
    public String addProduct(@RequestParam Long id, @RequestParam String title, Model model) {
        cartService.addProduct(id, title);
        model.addAttribute("cart", cartService.findAllProducts());
        return "cart_page";
    }
}
