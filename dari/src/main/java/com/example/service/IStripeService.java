package com.example.service;


import com.example.entity.ChargeRequest;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;


public interface IStripeService {
	public String createStripeCustomer(String userName);

	public String createCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc) throws StripeException ;

	public String paymentIntent(ChargeRequest chargerequest)throws StripeException ;

	public PaymentIntent confirm(String id) throws StripeException;
	
	public PaymentIntent cancel(String id) throws StripeException ;
	
	public PaymentIntent Capture(String id) throws StripeException ;
	
	public Long charge(ChargeRequest chargeRequest) throws StripeException ;
}
