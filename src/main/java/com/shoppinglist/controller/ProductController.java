package com.shoppinglist.controller;

import com.shoppinglist.domain.ProductDto;
import com.shoppinglist.mapper.ProductMapper;
import com.shoppinglist.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.List;

@CrossOrigin(origins = "*")
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

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException {
        return productMapper.mapToProductDto(dbService.getProduct(productId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        dbService.deleteProduct(productId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapToProductDto(dbService.saveProduct(productMapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        dbService.saveProduct(productMapper.mapToProduct(productDto));
    }
}
