package com.ekart.ecom.product.service;

import com.ekart.ecom.product.dto.OrderDTO;
import com.ekart.ecom.product.dto.PaymentDTO;
import com.ekart.ecom.product.enums.OrderStatus;
import com.ekart.ecom.product.enums.PaymentStatus;
import com.ekart.ecom.product.exceptions.AddressNotFoundException;
import com.ekart.ecom.product.exceptions.CustomerNotFoundException;
import com.ekart.ecom.product.exceptions.ProductNotFoundException;
import com.ekart.ecom.product.model.*;
import com.ekart.ecom.product.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

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
    private CustomerService customerService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartsProductsService cartsProductsService;

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

    public List<OrderDTO> payForCart(@NotNull final Long customerId, @NotNull final PaymentDTO paymentDTO) throws CustomerNotFoundException, AddressNotFoundException {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        Customer customer;
        try {
            customer = customerService.getCustomer(customerId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CustomerNotFoundException("Customer with id " + customerId + " not found");
        }
        final List<CartsProduct> cartsProducts = cartsProductsService.findByCartId(customer.getCart().getId());
        AtomicReference<Double> cartPrice = new AtomicReference<>(0.0);
        List<Order> orders = new ArrayList<>();
        cartsProducts.forEach(cartsProduct -> {
            cartPrice.set(cartPrice.get() + (cartsProduct.getUnits() * cartsProduct.getProduct().getBuyPrice()));
            orders.add(Order.builder()
                    .product(cartsProduct.getProduct())
                    .createdAt(new Date())
                    .shipTo(Address.builder().id(paymentDTO.getAddressId()).build())
                    .build());
        });
        final Payment payment = paymentRepository.save(
                Payment.builder()
                        .paymentDate(new Date())
                        .status(PaymentStatus.SUCCESS).amount(cartPrice.get()).build()
        );
        Address deliveryAddress;
        try {
            deliveryAddress = addressService.getAddress(paymentDTO.getAddressId()).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new AddressNotFoundException("Address " + paymentDTO.getAddressId() + " not found");
        }
        orders.forEach(order -> {
            order.setStatus(OrderStatus.PLACED);
            //TODO create separate payments for each order
            order.setPayment(payment);
            order.setShipTo(deliveryAddress);
            orderService.create(order);
            orderDTOS.add(OrderDTO.builder()
                    .createdAt(order.getCreatedAt())
                    .orderId(order.getId())
                    .shipFromAddress(order.getProduct().getVendor().getAddress())
                    .shipToAddress(deliveryAddress)
                    .orderStatus(order.getStatus())
                    .payment(payment)
                    .build());
        });
        return orderDTOS;
    }
}
