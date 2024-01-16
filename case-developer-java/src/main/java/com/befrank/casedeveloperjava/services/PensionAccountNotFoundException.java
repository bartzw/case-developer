package com.befrank.casedeveloperjava.services;

public class PensionAccountNotFoundException extends RuntimeException {

    public PensionAccountNotFoundException(final String message) {
        super(message);
    }
}
