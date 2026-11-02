package com.automationexercise.tests;

import com.automationexercise.model.User;
import com.automationexercise.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDataFactoryTest {

    @Test
    public void everyGeneratedUserHasAUniqueEmail() {
        User first = TestDataFactory.newUser();
        User second = TestDataFactory.newUser();
        Assert.assertNotEquals(first.email(), second.email());
    }

    @Test
    public void invalidPaymentHasNoCardNumber() {
        Assert.assertTrue(TestDataFactory.invalidPayment().cardNumber().isEmpty());
    }

    @Test
    public void invalidContactMessageHasMalformedEmail() {
        Assert.assertFalse(TestDataFactory.invalidContactMessage().email().contains("@"));
    }
}
