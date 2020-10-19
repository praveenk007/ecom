package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.Order;
import com.ekart.ecom.product.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order create(@NotNull final Order order) {
        return orderRepository.save(order);
    }
}
