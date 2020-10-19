package com.ekart.ecom.product.service;

import com.ekart.ecom.product.exceptions.CustomerNotFoundException;
import com.ekart.ecom.product.model.Cart;
import com.ekart.ecom.product.model.CartsProduct;
import com.ekart.ecom.product.model.Customer;
import com.ekart.ecom.product.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Service
public class CartService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartsProductsService cartsProductsService;

    public void addProduct(final Long userId, final Long productId) throws CustomerNotFoundException {
        Customer customer;
        try {
            customer = customerService.getCustomer(userId)
                .orElseThrow();
            final Cart cart = customer.getCart();
            if(null == cart.getProducts() || cart.getProducts().isEmpty()) {
                if(null == cart.getProducts()) {
                    cart.setProducts(new ArrayList<>());
                }
            } else {
                List<CartsProduct> cartsProducts = cartsProductsService.findByCartId(cart.getId());
                for(CartsProduct cartsProduct: cartsProducts) {
                    if (cartsProduct.getProductId().equals(productId)) {
                        cartsProduct.setUnits(cartsProduct.getUnits() + 1);
                        cartsProductsService.save(cartsProduct);
                        return;
                    }
                }
            }
            cartsProductsService.save(CartsProduct.builder().productId(productId).cartId(cart.getId()).units(1L).build());
        } catch (NoSuchElementException e) {
            throw new CustomerNotFoundException("User " + userId + " not found");
        }
    }
}
