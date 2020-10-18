package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.Product;
import com.ekart.ecom.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(@NotNull final Product product) {
        return  productRepository.save(product);
    }
}
