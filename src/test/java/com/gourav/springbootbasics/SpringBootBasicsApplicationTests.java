package com.gourav.springbootbasics;

import com.gourav.springbootbasics.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
class SpringBootBasicsApplicationTests {

    @Test
    void testGetProducts() {
        RestTemplate restTemplate = new RestTemplate();

        List<Product> products = restTemplate.getForObject("http://localhost:8080/products", List.class);

        Assertions.assertNotNull(products);
    }

    @Test
    void testGetProduct() {
        RestTemplate restTemplate = new RestTemplate();

        Product product = restTemplate.getForObject("http://localhost:8080/products/1", Product.class);

        Assertions.assertNotNull(product);
        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("Watch", product.getName());
        Assertions.assertEquals(1500, product.getPrice());
    }

    @Test
    void testCreateProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product(5, "Shoes", 3000);

        Product newProduct = restTemplate.postForObject("http://localhost:8080/products", product, Product.class);

        Assertions.assertNotNull(newProduct);
        Assertions.assertEquals(5, newProduct.getId());
        Assertions.assertEquals("Shoes", newProduct.getName());
        Assertions.assertEquals(3000, newProduct.getPrice());
    }

    @Test
    void testUpdateProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject("http://localhost:8080/products/2", Product.class);
        assert product != null;
        product.setPrice(1000);

        restTemplate.put("http://localhost:8080/products", product);
        Product updatedProduct = restTemplate.getForObject("http://localhost:8080/products/2", Product.class);

        assert updatedProduct != null;
        Assertions.assertEquals(1000, updatedProduct.getPrice());
    }
}
