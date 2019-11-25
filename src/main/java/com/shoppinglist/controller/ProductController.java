package com.shoppinglist.controller;

import com.shoppinglist.domain.ProductDto;
import com.shoppinglist.mapper.ProductMapper;
import com.shoppinglist.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shoppingList/products")
public class ProductController {

    @Autowired
    private DbService dbService;
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(dbService.getAllProducts());
    }

    public ProductDto getProduct() {
        return new ProductDto(1L, "productName");
    }

    public void deleteProduct(Long taskId) {

    }

    public ProductDto updateProduct(ProductDto productDto) {
        return new ProductDto(1L, "productName");
    }

    public void createProduct(ProductDto productDto) {

    }
}
