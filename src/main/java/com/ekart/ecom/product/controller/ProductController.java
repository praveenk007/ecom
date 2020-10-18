package com.ekart.ecom.product.controller;

import com.ekart.ecom.product.dto.ProductDTO;
import com.ekart.ecom.product.model.Product;
import com.ekart.ecom.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kamathp
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/product/0.0.1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody final ProductDTO productDTO) {
        final Product product = productService.createProduct(productDTO.toModel());
        return ResponseEntity.status(HttpStatus.OK).body(ProductDTO.toDTO(product));
    }
}
