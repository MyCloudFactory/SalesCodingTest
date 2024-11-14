package org.itsf.business.exceptions;

public class BadLineFormatException extends Exception {

    public static final String QUANTITYUNKNOWN = "quantity unknown";
    public static final String PRICEUNKNOWN = "price unknown";

    public BadLineFormatException(String message) {
        super(message);
    }
}
