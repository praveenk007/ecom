package com.ekart.ecom.product.exceptions;

/**
 * @author kamathp
 * @version 0.0.1
 */
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
