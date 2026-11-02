package com.automationexercise.model;

/** Dummy card data for the payment form. */
public record PaymentDetails(
        String nameOnCard,
        String cardNumber,
        String cvc,
        String expiryMonth,
        String expiryYear) {
}
