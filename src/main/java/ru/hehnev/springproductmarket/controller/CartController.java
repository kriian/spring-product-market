package ru.hehnev.springproductmarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hehnev.springproductmarket.service.CartService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public String findAllProducts(Model model) {
        model.addAttribute("cart", cartService.findAllProducts());
        return "cart_page";
    }

    @GetMapping("/add")
    public String addProduct(@RequestParam Long id, @RequestParam String title, @RequestParam Integer price, Model model) {
        cartService.addProduct(id, title, price);
        model.addAttribute("cart", cartService.findAllProducts());
        return "cart_page";
    }
}
