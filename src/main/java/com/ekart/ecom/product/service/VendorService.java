package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.Vendor;
import com.ekart.ecom.product.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor create(@NotNull Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}