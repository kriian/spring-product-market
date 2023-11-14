package ru.hehnev.springproductmarket.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hehnev.springproductmarket.model.Product;
import ru.hehnev.springproductmarket.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Nested
    @DisplayName("Тесты GET получение продуктов")
    class GetMethodTest {
        @Test
        @DisplayName("Получение всех продуктов")
        void findAll() {
            List<Product> expectedListProducts = List.of(
                    new Product(1L, "Nike", 7500),
                    new Product(2L, "Reebok", 9900)
            );
            when(productRepository.findAll()).thenReturn(expectedListProducts);
            List<Product> actualListProducts = productRepository.findAll();
            assertThat(actualListProducts)
                    .isEqualTo(expectedListProducts)
                    .hasSize(2);
            verify(productRepository, times(1)).findAll();
        }

        @Test
        @DisplayName("Получение продукта по id")
        void findById() {
            Long id = 1L;
            Product expectedProduct = new Product(id, "Nike", 7500);
            when(productRepository.findById(id)).thenReturn(Optional.of(expectedProduct));
            Product actualProduct = productServiceImpl.findById(id);
            assertEquals(actualProduct, expectedProduct);
            assertEquals("Nike", actualProduct.getTitle());
            verify(productRepository, times(1)).findById(id);
        }

        @Test
        @DisplayName("Получение продукта по id с ошибкой")
        void findByIdWithNoResult() {
            final Long id = 99L;
            NoSuchElementException exception =
                    assertThrows(NoSuchElementException.class, () -> productServiceImpl.findById(id));
            assertThat(exception.getMessage()).isEqualTo("No such product. id = " + id);
        }
    }

    @Test
    void addProduct() {
    }

    @Test
    void changeProductPrice() {
    }

    @Test
    void deleteProduct() {
    }
}