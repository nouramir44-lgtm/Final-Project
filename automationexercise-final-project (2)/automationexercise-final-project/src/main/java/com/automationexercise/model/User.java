package com.automationexercise.model;

/** Account data used for registration, login and delivery-address validation. */
public record User(
        String name,
        String email,
        String password,
        String title,
        String birthDay,
        String birthMonth,
        String birthYear,
        String firstName,
        String lastName,
        String company,
        String address1,
        String address2,
        String country,
        String state,
        String city,
        String zipcode,
        String mobileNumber) {
}
