package com.ekart.ecom.product.repositories;

import com.ekart.ecom.product.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
