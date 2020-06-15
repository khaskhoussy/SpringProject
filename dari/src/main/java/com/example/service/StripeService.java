package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.entity.ChargeRequest;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.stripe.Stripe;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;



@Service
public class StripeService implements IStripeService{

	@Autowired
	UserRepository userRepository;
	
	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;

	@PostConstruct
	public void init() {
		Stripe.apiKey = secretKey;
	}
	
	
	@Override
	public String createStripeCustomer(String userName) {
		
		// stripe key
		Stripe.apiKey = "sk_test_or9aEFfs3K60YZehyL6Im5ed00ju1JHlxO";
////yomkon erreur lehné emba3ed
		User user = userRepository.findByUserName(userName).get();
		Map<String, Object> params = new HashMap<>();
		params.put("description", "My First Test Customer (created for API docs)");
		params.put("email", user.getMailAddress());

		// affichage id du customer
		try {
			Customer customer = Customer.create(params);

			System.out.println("create customer id: {}");
			return customer.getId();
		} catch (StripeException e) {

			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub
//		return null;
	}

	@Override
	public String createCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc)
			throws StripeException {
		// TODO Auto-generated method stub
		//return null;
		// stripe key
		Stripe.apiKey = "sk_test_or9aEFfs3K60YZehyL6Im5ed00ju1JHlxO";

		Customer customer = Customer.retrieve(customerId);

		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number", carta);
		cardParam.put("exp_month", expMonth);
		cardParam.put("exp_year", expYear);
		cardParam.put("cvc", cvc);

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam);

		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

		customer.getSources().create(source);
		return token.getId();
	}

	@Override
	public String paymentIntent(ChargeRequest chargerequest)throws StripeException{
		// TODO Auto-generated method stub
		//return null;
		// stripe key
		Stripe.apiKey = "sk_test_or9aEFfs3K60YZehyL6Im5ed00ju1JHlxO";

		// `source` is obtained with Stripe.js; see
		// https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
		List<String> paymentMethodTypes = new ArrayList();
		paymentMethodTypes.add("card");
		
		
		Map<String, Object> params = new HashMap<>();
		params.put("amount",chargerequest.getAmount());
		params.put("currency", chargerequest.getCurrency());
		params.put("description", chargerequest.getDescription());
		params.put("payment_method_types", paymentMethodTypes);
		
		PaymentIntent p = PaymentIntent.create(params);
		p.getId();
		//Charge charge = Charge.create(params);
		return p.getId();
	}
	
	
	
	/*
	 * this methode is to confirm that your customer intends to pay with current
	 * or provided payment method. Upon confirmation, the PaymentIntent will
	 * attempt to initiate a payment
	 */
	public PaymentIntent confirm(String id) throws StripeException {
		Stripe.apiKey = "sk_test_or9aEFfs3K60YZehyL6Im5ed00ju1JHlxO";
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		Map<String, Object> params = new HashMap<>();
		params.put("payment_method", "pm_card_visa");
		// params.put("customer", "cus_H1OvsnwEn1KX36");
		paymentIntent.confirm(params);
		return paymentIntent;
	}
	

	public PaymentIntent cancel(String id) throws StripeException {
		Stripe.apiKey = "sk_test_or9aEFfs3K60YZehyL6Im5ed00ju1JHlxO";
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		PaymentIntent paymentIntent1 = paymentIntent.cancel();
		return paymentIntent1;
	}

	
	/*
	 * If payment succeeds, the PaymentIntent will transition to the succeeded
	 * status (or requires_capture, if capture_method is set to manual). the
	 * role of this methode is to Capture the funds of an existing uncaptured
	 * PaymentIntent when its status is requires_capture. Uncaptured
	 * PaymentIntents will be canceled exactly seven days after they are
	 * created.
	 */
	public PaymentIntent Capture(String id) throws StripeException {
		Stripe.apiKey = "sk_test_or9aEFfs3K60YZehyL6Im5ed00ju1JHlxO";
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		PaymentIntent paymentIntent1 = paymentIntent.capture();
		return paymentIntent1;
	}
	
	public Long charge(ChargeRequest chargeRequest) throws StripeException {

		// stripe key
		Stripe.apiKey = "sk_test_or9aEFfs3K60YZehyL6Im5ed00ju1JHlxO";

		// `source` is obtained with Stripe.js; see
		// https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
		Map<String, Object> params = new HashMap<>();
		params.put("amount", chargeRequest.getAmount());
		params.put("currency", chargeRequest.getCurrency());
		params.put("description", chargeRequest.getDescription());
		// add the Stripe_customer_id to the entity
		//params.put("customer", "cus_H1OvsnwEn1KX36");
		params.put("customer", "cus_H1OvsnwEn1KX36");
		Charge charge = Charge.create(params);
		return charge.getAmount();
	}
	
}
