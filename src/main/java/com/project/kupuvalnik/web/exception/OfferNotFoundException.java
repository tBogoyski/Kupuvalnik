package com.project.kupuvalnik.web.exception;

public class OfferNotFoundException extends RuntimeException {

    private final Long id;

    public OfferNotFoundException(Long offerId) {

        super("Offer with id " + offerId + " is not found!");
        this.id = offerId;
    }
}
