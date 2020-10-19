package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.Address;
import com.ekart.ecom.product.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Optional<Address> getAddress(final Long addressId) {
        return addressRepository.findById(addressId);
    }
}
