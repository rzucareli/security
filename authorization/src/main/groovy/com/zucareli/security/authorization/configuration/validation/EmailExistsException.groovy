package com.zucareli.security.authorization.configuration.validation

class EmailExistsException extends Throwable {
    EmailExistsException(final String message) {
        super(message)
    }
}
