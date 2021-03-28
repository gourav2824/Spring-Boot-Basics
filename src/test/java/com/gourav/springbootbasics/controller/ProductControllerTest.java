package com.gourav.springbootbasics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gourav.springbootbasics.model.Product;
import com.gourav.springbootbasics.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductRepository repository;

    private final String PRODUCTS_URL = "http://localhost:8080/products";

    @Test
    void testGetProductsApiCall() throws Exception {
        Product product = new Product(1, "Shoes", 4000);
        List<Product> products = Collections.singletonList(product);
        when(repository.findAll()).thenReturn(products);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedJsonString = objectWriter.writeValueAsString(products);

        mockMvc.perform(get(PRODUCTS_URL)).andExpect(status().isOk()).andExpect(content().json(expectedJsonString));
    }

    @Test
    void testGetProductApiCall() throws Exception {
        int productId = 3;
        Product product = new Product(productId, "Shirt", 1000);
        when(repository.findById(productId)).thenReturn(java.util.Optional.of(product));

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedJsonString = objectWriter.writeValueAsString(product);

        mockMvc.perform(get(PRODUCTS_URL + "/" + productId)).andExpect(status().isOk())
                .andExpect(content().json(expectedJsonString));
    }

    @Test
    void testCreateProductApiCall() throws Exception {
        Product product = new Product(1, "Bag", 1500);
        when(repository.save(any())).thenReturn(product);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String productJsonString = objectWriter.writeValueAsString(product);

        mockMvc.perform(post(PRODUCTS_URL).contentType(MediaType.APPLICATION_JSON).content(productJsonString))
                .andExpect(status().isOk()).andExpect(content().json(productJsonString));
    }

    @Test
    void testUpdateProductApiCall() throws Exception {
        Product product = new Product(2, "Smartphone", 20000);
        product.setPrice(15000);
        when(repository.save(any())).thenReturn(product);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String productJsonString = objectWriter.writeValueAsString(product);

        mockMvc.perform(put(PRODUCTS_URL).contentType(MediaType.APPLICATION_JSON).content(productJsonString))
                .andExpect(status().isOk()).andExpect(content().json(productJsonString));
    }

    @Test
    void testDeleteProductApiCall() throws Exception {
        int productId = 2;
        doNothing().when(repository).deleteById(productId);

        mockMvc.perform(delete(PRODUCTS_URL + "/" + productId)).andExpect(status().isOk());
    }
}
