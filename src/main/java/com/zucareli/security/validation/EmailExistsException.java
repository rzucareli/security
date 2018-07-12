package com.zucareli.security.validation;

public class EmailExistsException extends Exception {

    public EmailExistsException(final String message) {
        super(message);
    }

}
