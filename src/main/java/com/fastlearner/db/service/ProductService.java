package com.fastlearner.db.service;

import com.fastlearner.db.dto.ProductDTO;
import com.fastlearner.db.entity.Product;
import com.fastlearner.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    public Product addProduct(ProductDTO product)
    {
        log.debug("{} | Adding product with name : {}", LocalDateTime.now(), product.getName());
        Product existingProduct = productRepository.findByName(product.getName()).orElse(null);
        if (existingProduct != null)
        {
            log.debug("Product with name {} already exists", product.getName());
            return existingProduct;
        }
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setCategory(product.getCategory());
        newProduct.setCreatedAt(LocalDateTime.now());
        return productRepository.save(newProduct);
    }
    public List<Product> getAllProducts()
    {
        log.debug("Retrieving all products");
        return productRepository.findAll();
    }
    public Product findById(Long id)
    {
        log.debug("Retrieving product with id : {}", id);
        return productRepository.findById(id).orElse(null);
    }
    public List<Product> findByCategory(String category)
    {
        log.debug("Retrieving all products by category : {}", category);
        return productRepository.findByCategory(category);
    }
}
