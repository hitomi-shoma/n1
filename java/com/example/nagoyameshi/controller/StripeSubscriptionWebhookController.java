package com.example.nagoyameshi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.nagoyameshi.service.StripeSubscriptionService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

@Controller
public class StripeSubscriptionWebhookController {
	private final StripeSubscriptionService stripeSubscriptionService;
	
	@Value("${stripe.api-key}")
    private String stripeApiKey;
	
	@Value("${stripe.webhook-secret}")
    private String webhookSecret;
	
	public StripeSubscriptionWebhookController(StripeSubscriptionService stripeSubscriptionService) {
		this.stripeSubscriptionService = stripeSubscriptionService;
	}

	@PostMapping("/stripe/subscription-webhook")
	public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
	    Stripe.apiKey = stripeApiKey;
	    Event event = null;

	    try {
	        event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
	    } catch (SignatureVerificationException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	    if ("checkout.session.completed".equals(event.getType())) {
	        stripeSubscriptionService.processSubscriptionSessionCompleted(event);
	    }

	    return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}