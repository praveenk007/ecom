package com.ekart.ecom.product.model;

import com.ekart.ecom.product.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
/*
    @Column(name = "customer_id")
    private Long customerId;*/

    @OneToOne
    @JoinTable(name = "customers_orders", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private Customer customer;

    @OneToOne
    @JoinTable(name = "orders_address", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "to_address_id")})
    private Address shipTo;

    @OneToOne
    @JoinTable(name = "orders_products", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Product product;

    @OneToOne
    @JoinTable(name = "orders_payments", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "payment_id")})
    private Payment payment;

    private OrderStatus status;

    private Date createdAt;

    private Date expectedDeliveryDate;
}
