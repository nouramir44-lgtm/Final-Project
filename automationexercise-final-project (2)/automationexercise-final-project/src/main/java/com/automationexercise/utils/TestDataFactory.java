package com.automationexercise.utils;

import com.automationexercise.model.ContactMessage;
import com.automationexercise.model.PaymentDetails;
import com.automationexercise.model.User;

import java.util.concurrent.atomic.AtomicInteger;

/** Builds unique, dummy test data. */
public final class TestDataFactory {

    private static final String DEFAULT_PASSWORD = "Test@12345";
    private static final AtomicInteger COUNTER = new AtomicInteger();

    private TestDataFactory() {
    }

    /** A new user with a unique name and email so registration never depends on existing accounts. */
    public static User newUser() {
        String unique = System.currentTimeMillis() + "" + COUNTER.incrementAndGet();
        return new User(
                "AutoUser" + unique,
                "auto.user." + unique + "@example.com",
                DEFAULT_PASSWORD,
                "Mr",
                "15",
                "May",
                "1990",
                "Auto",
                "Tester",
                "Automation Co",
                "12 Test Street",
                "Building 4",
                "Canada",
                "Ontario",
                "Toronto",
                "M5V2T6",
                "5551234567");
    }

    public static PaymentDetails validPayment() {
        return new PaymentDetails("Auto Tester", "4111111111111111", "123", "12", "2030");
    }

    /** Empty card data: every payment field is required, so the form must not submit. */
    public static PaymentDetails invalidPayment() {
        return new PaymentDetails("", "", "", "", "");
    }

    public static ContactMessage validContactMessage() {
        return new ContactMessage(
                "Auto Tester",
                "auto.tester@example.com",
                "Automation test inquiry",
                "This message was sent by the automated test suite.");
    }

    public static ContactMessage invalidContactMessage() {
        return new ContactMessage(
                "Auto Tester",
                "invalid-email",
                "Automation test inquiry",
                "This message uses an invalid email address.");
    }
}
