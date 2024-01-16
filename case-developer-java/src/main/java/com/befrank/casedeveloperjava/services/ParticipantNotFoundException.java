package com.befrank.casedeveloperjava.services;

public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException(final String message) {
        super(message);
    }
}
