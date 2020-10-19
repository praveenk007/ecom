package com.ekart.ecom.product.service;

import com.ekart.ecom.product.model.CartsProduct;
import com.ekart.ecom.product.repositories.CartsProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class CartsProductsService {

    @Autowired
    private CartsProductsRepository cartsProductsRepository;

    public List<CartsProduct> findByCartId(final Long cartId) {
        final List<Long> ids = new ArrayList<>();
        ids.add(cartId);
        return cartsProductsRepository.findAllById(ids);
    }

    public CartsProduct save(@NotNull final CartsProduct cartsProduct) {
        return cartsProductsRepository.save(cartsProduct);
    }
}
