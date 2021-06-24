package com.inditex.prices.exceptions;

/**
 * Exception usada cuando el forma de fecha recibido no es correcto.
 *
 * @author Eze Q.
 */
public class DatePriceFormatException extends Exception {

    public DatePriceFormatException() {
    }

    public DatePriceFormatException(String message) {
        super(message);
    }

}
