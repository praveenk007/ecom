package com.ekart.ecom.product.exceptions;

/**
 * @author kamathp
 * @version 0.0.1
 */
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
