package com.shoppinglist.service;

import com.shoppinglist.controller.ProductNotFoundException;
import com.shoppinglist.domain.Product;
import com.shoppinglist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(final Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(final Long productId) {
        productRepository.deleteById(productId);
    }
}
