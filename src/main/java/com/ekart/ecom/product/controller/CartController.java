package com.ekart.ecom.product.controller;

import com.ekart.ecom.product.exceptions.CustomerNotFoundException;
import com.ekart.ecom.product.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kamathp
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/cart/v1.0")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/product/{productId}/add")
    public String addProduct(@PathVariable("productId") final Long productId, @RequestHeader("user_id") final Long userId) {
        try {
            cartService.addProduct(userId, productId);
        } catch (CustomerNotFoundException e) {
            return "not found";
        }
        return "hey";
    }

}
