package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.Product;
import com.ekart.ecom.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(@NotNull final Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(@NotNull Long productId) {
        return productRepository.findById(productId);
    }
}
