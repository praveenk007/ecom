package com.ekart.ecom.product.dto;

import com.ekart.ecom.product.enums.OrderStatus;
import com.ekart.ecom.product.model.Address;
import com.ekart.ecom.product.model.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private Payment payment;

    private Address shipFromAddress;

    private Address shipToAddress;

    private Long orderId;

    private OrderStatus orderStatus;

    private Date createdAt;

    private Date expectedDeliveryTime;
}
