package com.rohan.airBnb.Service;


import com.rohan.airBnb.Entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}
