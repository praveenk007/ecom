package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.Brand;
import com.ekart.ecom.product.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand create(@NotNull final Brand brand) {
        return brandRepository.save(brand);
    }
}
