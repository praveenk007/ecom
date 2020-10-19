package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.Customer;
import com.ekart.ecom.product.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomer(final Long customerId) {
        return customerRepository.findById(customerId);
    }
}
