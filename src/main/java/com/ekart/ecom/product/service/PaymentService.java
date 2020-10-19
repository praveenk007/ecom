package com.ekart.ecom.product.service;

import com.ekart.ecom.product.dto.OrderDTO;
import com.ekart.ecom.product.dto.PaymentDTO;
import com.ekart.ecom.product.enums.OrderStatus;
import com.ekart.ecom.product.enums.PaymentStatus;
import com.ekart.ecom.product.exceptions.AddressNotFoundException;
import com.ekart.ecom.product.exceptions.ProductNotFoundException;
import com.ekart.ecom.product.model.Address;
import com.ekart.ecom.product.model.Order;
import com.ekart.ecom.product.model.Payment;
import com.ekart.ecom.product.model.Product;
import com.ekart.ecom.product.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class PaymentService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    public OrderDTO payForProduct(final Long productId, final PaymentDTO paymentForProductDTO) throws ProductNotFoundException, AddressNotFoundException {
        Product product;
        try {
            product = productService.getProduct(productId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ProductNotFoundException("Product " + productId + " not found");
        }
        final Payment payment = paymentRepository.save(
            Payment.builder()
                    .paymentDate(new Date())
                    .status(PaymentStatus.SUCCESS).amount(product.getBuyPrice()).build()
        );
        Address deliveryAddress;
        try {
            deliveryAddress = addressService.getAddress(paymentForProductDTO.getAddressId()).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new AddressNotFoundException("Address " + paymentForProductDTO.getAddressId() + " not found");
        }
        final Order order = Order.builder()
                .product(product)
                .createdAt(new Date()).payment(payment)
                .shipTo(Address.builder().id(paymentForProductDTO.getAddressId()).build())
                .status(OrderStatus.PLACED).build();
        Order createdOrder = orderService.create(order);
        return OrderDTO.builder()
                .createdAt(createdOrder.getCreatedAt())
                .orderId(createdOrder.getId())
                .shipFromAddress(product.getVendor().getAddress())
                .shipToAddress(deliveryAddress)
                .orderStatus(createdOrder.getStatus())
                .payment(payment)
                .build();
    }
}
