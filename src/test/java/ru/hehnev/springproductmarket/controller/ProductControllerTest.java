package ru.hehnev.springproductmarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hehnev.springproductmarket.model.Product;
import ru.hehnev.springproductmarket.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getProductById() throws Exception {
        Long productId = 1L;
        Product product = new Product(productId, "Nike", 7500);
        when(productService.findById(productId)).thenReturn(product);
        mockMvc.perform(get("/products/" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.title").value("Nike"))
                .andExpect(jsonPath("$.price").value(7500));
        verify(productService, times(1)).findById(productId);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void getAllProducts() throws Exception {
        List<Product> mockProducts = List.of(
                new Product(1L, "Nike", 7500),
                new Product(2L, "Reebok", 9900));
        when(productService.findAll()).thenReturn(mockProducts);
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Nike"))
                .andExpect(jsonPath("$[0].price").value(7500))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Reebok"))
                .andExpect(jsonPath("$[1].price").value(9900));
        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    void addProduct() throws Exception {
        Product product = new Product(1L, "Nike", 7500);
        String asJsonString = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/products")
                        .content(asJsonString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(productService, times(1)).addProduct(product);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void changePrice() throws Exception {
        Long productId = 1L;
        Integer newPrice = 150;

        mockMvc.perform(get("/products/change_price")
                        .param("id", productId.toString())
                        .param("price", newPrice.toString()))
                .andExpect(status().isOk());
        verify(productService, times(1)).changeProductPrice(productId, newPrice);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void deleteProduct() throws Exception {
        Long productId = 1L;
        mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isOk());
        verify(productService, times(1)).deleteProduct(productId);
        verifyNoMoreInteractions(productService);
    }
}