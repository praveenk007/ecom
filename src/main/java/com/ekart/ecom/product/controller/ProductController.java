package com.ekart.ecom.product.controller;

import com.ekart.ecom.product.dto.ProductDTO;
import com.ekart.ecom.product.dto.Response;
import com.ekart.ecom.product.model.Product;
import com.ekart.ecom.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Response<ProductDTO>> create(@RequestBody final ProductDTO productDTO) {
        final Product product = productService.createProduct(productDTO.toModel());
        final Response<ProductDTO> response = new Response<>();
        response.setData(ProductDTO.toDTO(product));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProductDTO>> get(@PathVariable("id") final Long id) {
        final Product product = productService.getProduct(id);
        final Response<ProductDTO> response = new Response<>();
        if(null == product) {
            response.setMessage("Product not found for id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(ProductDTO.toDTO(product));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
