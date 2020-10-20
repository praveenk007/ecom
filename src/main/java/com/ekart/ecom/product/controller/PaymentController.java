package com.ekart.ecom.product.controller;

import com.ekart.ecom.product.dto.OrderDTO;
import com.ekart.ecom.product.dto.PaymentDTO;
import com.ekart.ecom.product.dto.Response;
import com.ekart.ecom.product.exceptions.AddressNotFoundException;
import com.ekart.ecom.product.exceptions.CustomerNotFoundException;
import com.ekart.ecom.product.exceptions.ProductNotFoundException;
import com.ekart.ecom.product.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kamathp
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/payment/v1.0")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<Response<OrderDTO>> makePaymentForProduct(@PathVariable("productId") final Long productId, @RequestBody final PaymentDTO paymentDTO) {
        final Response<OrderDTO> response = new Response<>();
        try {
            response.setData(paymentService.payForProduct(productId, paymentDTO));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ProductNotFoundException | AddressNotFoundException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/cart")
    public ResponseEntity<Response<List<OrderDTO>>> makePaymentForCart(@RequestHeader("user_id") final Long userId, @RequestBody final PaymentDTO paymentDTO) {
        final Response<List<OrderDTO>> response = new Response<>();
        try {
            response.setData(paymentService.payForCart(userId, paymentDTO));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AddressNotFoundException | CustomerNotFoundException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
