package com.fastlearner.db.controller;

import com.fastlearner.db.dto.ProductDTO;
import com.fastlearner.db.entity.Product;
import com.fastlearner.db.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDTO product)
    {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Product>> findByCategoryAndPriceBetween(@RequestParam String category,
                                                                       @RequestParam Integer start,
                                                                       @RequestParam Integer end)
    {
        return new ResponseEntity<>(productService.findByCategoryAndPriceBetween(category,start,end), HttpStatus.OK);
    }
}
