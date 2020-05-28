package com.example.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ChargeRequest;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.MailService;
import com.example.service.StripeService;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;



@RestController
@RequestMapping("/user/payment")
public class RestControlCharge {

	@Autowired
	private StripeService stripeService;

    @Autowired
    private MailService emailService;
    @Autowired
    UserRepository userRepository;
	
	
	@PostMapping("/customer1")
	@ResponseBody
	public String createCustomer(HttpServletResponse response,HttpServletRequest request) {
		return stripeService.createStripeCustomer(Home.connectedUser);
	}

	// http://localhost:8081/dari/servlet/customer/retour_ta3_methode_create_customer/4242424242424242/11/2026/123
	@PostMapping("/customer/{customerId}/{carta}/{expMonth}/{expYear}/{cvc}")
	@ResponseBody
	// @PreAuthorize("hasRole('USER')")
	public String createCustomer(@PathVariable("customerId") String customerId, @PathVariable("carta") String carta,
			@PathVariable("expMonth") String expMonth, @PathVariable("expYear") String expYear,
			@PathVariable("cvc") String cvc) throws StripeException {
		return stripeService.createCustumorStripe(customerId, carta, expMonth, expYear, cvc);
	}

	// http://localhost:8081/dari/servlet/paymentintent
	/*
	 * { "description":"test la methode payment", "amount":"10000",
	 * "currency":"eur" }
	 */
	@PostMapping("/paymentintent")
	// @PreAuthorize("hasRole('USER')")
	public String payment(@RequestBody ChargeRequest chargeRequest) throws StripeException {
		return stripeService.paymentIntent(chargeRequest);
	}

	// http://localhost:8081/dari/servlet/confirm/{id}
	@PostMapping("/confirm/{id}/{idUser}")
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> confirm(@PathVariable("id") String id,HttpServletResponse response,HttpServletRequest request) throws StripeException {
		PaymentIntent paymentIntent = stripeService.confirm(id);
		String paymentStr = paymentIntent.toJson();
		
    	User user = userRepository.findByUserName(Home.connectedUser).get();
        emailService.sendMail(user.getMailAddress(), "Test Subject", "Test mail");
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	}

	@PostMapping("/cancel/{id}")
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
		PaymentIntent paymentIntent = stripeService.cancel(id);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	}

	// http://localhost:8081/dari/servlet/charge
	/*
	 * { "description":"test la methode payment", "amount":"10000",
	 * "currency":"eur" }
	 */
	@PostMapping("/charge")
	@ResponseBody
	public Long charge(@RequestBody ChargeRequest chargeRequest) throws StripeException {
		return stripeService.charge(chargeRequest);
	}
	
	 // http://localhost:8081/dari/servlet/capture/{id}
    @PostMapping("/capture/{id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> capture(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = stripeService.Capture(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
    
    /*@PostMapping("/SendEmail/{id}")
//  @PreAuthorize("hasRole('USER')")
  public String SendEmail(@PathVariable("id") String id) throws StripeException {
      PaymentIntent paymentIntent = stripeService.Capture(id);
      String paymentStr = paymentIntent.toJson();
      return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
  }*/
    
}
