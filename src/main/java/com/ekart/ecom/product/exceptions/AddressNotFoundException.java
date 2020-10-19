package com.ekart.ecom.product.exceptions;

/**
 * @author kamathp
 * @version 0.0.1
 */
public class AddressNotFoundException extends Exception {

    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}
